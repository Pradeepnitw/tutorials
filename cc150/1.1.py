import sys

""" 
Implement an algorithm to determine if a string has all unique characters.
What if you cannot use additional data structures?
"""

def is_unique(s):
    """
        sort character array in place to determine whether it have all
        unique characters
    """
    sorted_chars = sorted(s)
    for i, c in enumerate(sorted_chars):
        if i == len(sorted_chars) - 1:
            return True
        if c == sorted_chars[i+1]:
            return False

if __name__ == '__main__':
    for arg in sys.argv[1:]:
        print arg
        print is_unique(arg)
