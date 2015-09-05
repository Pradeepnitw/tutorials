import csv
import argparse

if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="""Split csv into multiple files""")
    parser.add_argument(
        "--num-lines",
        type=int,
        action="store",
        help="Number of maximun lines of each new file (default=10,000)")
    parser.add_argument(
        "--file-name",
        action="store",
        help="The input cvs file name")
    args = parser.parse_args()
    if not args.num_lines:
        args.num_lines = 10000
    # reader = csv.reader(args.file_name)
    reader = open(args.file_name, 'r')
    for line_num, line in enumerate(reader.readlines()):
        if line_num % args.num_lines == 0:
            output = open("{}_{}".format(line_num / args.num_lines,
                                         args.file_name), 'wb')
        output.write(line)
        if line_num % args.num_lines == args.num_lines - 1:
            output.close()
    # output.write(line)
    # writer = csv.write(output, quoting=csv.QUOTE_ALL)
    # wr.writerow(mylist)
