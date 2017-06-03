
"""
    A magic index in an array A[1...n-1] is defined to be an index such that
    A[i] = i. Given a sorted array of distinct integers, write a method to find
    a magic index, if one exists, in array A.
"""

def magic_index(a):
    if not a:
        return False
    # Binary search for the magic index
    lo = 0
    hi = len(a)
    while lo < hi:
        idx = lo + (hi - lo)/2
        # mid val > idx go left
        if a[idx] > idx:
            hi = idx
        # mid val < idx go right
        elif a[idx] < idx:
            lo = idx + 1
        else:
            return idx



def run_tests():
    l = [-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13]
    assert magic_index(l) == 7
    print "All tests passed"

if __name__ == "__main__":
    run_tests()
