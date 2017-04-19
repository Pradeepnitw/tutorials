"""
    Tony Hoare 1980 Turing Award
"""
from random import random

mute = True

def swap(a, i, j):
    if i == j:
        return
    temp = a[i]
    a[i] = a[j]
    a[j] = temp


def knuth_shuffle(a):
    idx = 1
    while idx < len(a):
        j = int(random() * (idx + 1))
        swap(a, idx, j)
        idx += 1


def test_knuth_shuffle():
    l = range(20)
    knuth_shuffle(l)
    if not mute:
        print "test shuffle range(20)", l


def quicksort(a):
    """
        Shuffle
        Partition
        Sort
    """
    if not a:
        return
    knuth_shuffle(a)
    if not mute:
        print "a after shuffle", a
    _partition(a, 0, len(a) - 1)
    if not mute:
        print "a after partitions", a

def _partition(a, lo, hi):
    if not a or lo >= hi:
        return
    elif lo == hi - 1:
        if a[lo] > a[hi]:
            swap(a, lo, hi)
    p = a[lo]  # Pivotal element
    i = lo + 1
    j = hi
    if not mute:
        print "p={},lo={},hi={},a={}".format(p, lo, hi, a)
    while i < j:
        while i <= hi and a[i] <= p:
            i += 1
        while a[j] > p and j - 1 > lo:
            j -= 1
        if i < j:
            if not mute:
                print "i={},j={} swapping".format(i, j)
                print a
            swap(a, i, j)
            if not mute:
                print a
    if i > j:
        swap(a, lo, j)
    if not mute:
        print "p={},i={},j={},a={}".format(p, i, j, a)
    _partition(a, lo, j-1)
    _partition(a, i, hi)


def run_tests():
    test_knuth_shuffle()
    l = range(50)
    for i in range(100):
        quicksort(l)
        if not l == range(50):
            print "False assertion, l={}".format(l)
            assert False
    print "All tests passed"


if __name__ == "__main__":
    run_tests()
