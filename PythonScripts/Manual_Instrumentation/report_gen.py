#!/user/bin/python
# Author: guolu
# Date: Dec-11-2014

# ==============================
# ------------Usage-------------
# python report_gen
#    -d/--directory <path-to-cpp-source-code-directory>
#    -l/--log <path-and-file-name-to-log-file>
#    -v/--verbose
# ==============================


from __future__ import print_function
import os
import re
import sys, getopt

class CPPHitInfo:
    """This class contains two list of line numbers."""
    """func_list stores the begin statement of a function call"""
    """branch_list stores the branches that's covered"""

    def __init__(self, lines, file_name, verbose):
        self.func_list = []
        self.branch_list = []
        self.lines = lines
        self.file_name = file_name
        self.process_file(verbose)
        return

    def print_func_list(self):
        print("Printing Line Number of method call in class " + self.file_name)
        print(self.func_list)
        return

    def print_branch_list(self):
        print("Printing Line Number of branching hit in class " + self.file_name)
        print(self.branch_list)
        return

    def print_file_name(self):
        print(self.file_name)
        return

    def process_file(self, verbose):
        for line in self.lines:
            if line.find(self.file_name) == -1:
                continue
            if verbose:
                print(line)
            # BDCCompleteHandler.cpp:53(20) BDCCompleteHandler::BDCCompleteHandler BEGIN
            l = re.split(" ", line)
            # ['BDCCompleteHandler.cpp:53(20)', 'BDCCompleteHandler::BDCCompleteHandler', 'BEGIN']
            #print(str(l) + "\t\t\tFor file:" + self.file_name) 
            
            try:
                match_obj = re.search('\((.+?)\)', l[0])
                # Search for what's inside the ()
                line_num = match_obj.group(1)
                
                if (len(l) == 2):
                    self.branch_list.append(line_num)
                elif l[2].find("BEGIN") != -1:
                    self.func_list.append(line_num)
                #elif l[2].find("END") != -1:
                #elif l[2].find("RETURN") != -1:
                #elif l[2].find("THROW") != -1:
            except:
                print("Error appeared while processing hit info:" + line)
        return

def preprocess_base_directory(raw_list, file_name):
    hit_list = []
    for line in raw_list:
        if line.find(file_name) != -1:
            phrases = line.split("\"")
            for phrase in phrases:
                if phrase.find(file_name) != -1:
                    hit_list.append(phrase)
                    #print("Found Hit Phrase:" + phrase)
    return hit_list

def preprocess_log_file(raw_list):
    log_list = []
    for line in raw_list:
        line1 = repr(line)
        if line1.startswith('\''):
            line1 = line1[1:]
        while line1.startswith(' '):
            line1 = line1[1:]
        if line1.endswith('\''):
            line1 = line1[:-1]
        if line1.endswith('\\n'):
            line1 = line1[:-2]
        log_list.append(line1) 
    return log_list

def compare_cpp_hit_info(base_object, log_object):
    if base_object.file_name is not log_object.file_name:
        print("ERROR: You can ony compare hit data for same file")
        return        

    func_percent = 0
    branch_percent = 0    

    try:
        func_percent = 100 * len(log_object.func_list) / len(base_object.func_list)
        branch_percent = 100 * len(log_object.branch_list) / len(base_object.branch_list)
    except:
        pass
     
    print("Coverage Report for " + base_object.file_name);
    print("Method Total:" + str(len(base_object.func_list)) + " | Covered:" + str(len(log_object.func_list)) + " | " + str(func_percent) + "%")
    print("Branching Total:" + str(len(base_object.branch_list)) + " | Covered:" + str(len(log_object.branch_list)) + " | " + str(branch_percent) + "%")

def main(argv):
    path = "/x/local/guolu/githome/code/biz/FinSys/BankCard/Common/AuthServer/implementation/"
    log_path = "bdcSorted.log"
    verbose = False
    
    try:
        opts, args = getopt.getopt(argv,"d:l:v",["path=", "log_path=", "verbose="])
    except getopt.GetoptError:
        print("==============================")
        print("------------Usage-------------")
        print(" python report_gen")
        print("     -d/--directory <path-to-cpp-source-code-directory>")
        print("     -l/--log <path-and-file-name-to-log-file>")
        print("     -v/--verbose")
        print("==============================")
        sys.exit(2)

    for opt, arg in opts:
        if opt in ("-d", "--directory"):
            path = arg
        elif opt in ("-l", "--log"):
            log_path = arg
        elif opt in ("-v", "--verbose"):
            verbose = True
    if not path.endswith("/"):
        path = path + "/"
            
    print('CPP Directory:', path)
    print('Log File:', log_path)
    print('Print Line Details:', verbose)

    cpp_name_list = []
    # map storing the original cpp hit info, key is the cpp file name, value is the CPPHitInfo class
    base_map = {}
    log_map = {}
   
    if verbose:
        print("Getting list of cpp files from:" + path)
    for file_name in os.listdir(path):
        if file_name.endswith(".cpp"):
            cpp_name_list.append(file_name)

    for cpp_name in cpp_name_list:
        if verbose:
            print("Processing " + cpp_name)
        f = open(path + cpp_name)
        lines = f.readlines()
        f.close()
        lines = preprocess_base_directory(lines, cpp_name)
        file_info = CPPHitInfo(lines, cpp_name, verbose)
        #file_info.print_func_list()
        #file_info.print_branch_list()
        base_map[cpp_name] = file_info
    print("Total number of files processed:" + str(len(base_map)))

    f = open(log_path)
    lines = f.readlines()
    f.close()
    log_raw_list = preprocess_log_file(lines)

    for cpp_name in cpp_name_list:
        if verbose:
            print("Searching Through Log for " + cpp_name)
        log_info = CPPHitInfo(log_raw_list, cpp_name, verbose)
        #log_info.print_func_list()
        #log_info.print_branch_list()
        log_map[cpp_name] = log_info
    print("Total number of cpp file logs processed:" + str(len(log_map)))

    for cpp_name in cpp_name_list:
        print("Comparing base data with log data for " + cpp_name)
        compare_cpp_hit_info(base_map[cpp_name], log_map[cpp_name])

if __name__ == "__main__":
    main(sys.argv[1:])
