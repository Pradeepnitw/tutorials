#!/user/bin/python
# Author: Fiona Lu
# Date: 03/18/2015

import sys

def main(argv):
    file_name = "dic.txt"
    
    file = open(file_name)
    lines = file.readlines()
    file.close()

if __name__ == "__main__":
    main(sys.argv[1:])
