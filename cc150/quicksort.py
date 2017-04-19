"""
    Tony Hoare 1980 Turing Award
"""
from random import random

mute = False

def swap(a, i, j):
    if i == j:
        return
    temp = a[i]
    a[i] = a[j]
    a[j] = temp


def knuth_shuffle(a):
    for i in range(len(a)):
        ran = int(random()*(i+1))
        swap(a, i, ran)


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
    i = lo + 1
    j = hi
    if not mute:
        print "p={},lo={},hi={},a={}".format(a[lo], lo, hi, a)
    while i <= j:
        while i <= hi and a[i] <= a[lo]:
            i += 1
        while a[j] > a[lo]:
            j -= 1
        if j == lo:
            break
        if i > j:
            i = lo
        if not mute:
            print "i={},j={} swapping".format(i, j)
            print a
        swap(a, i, j)
        if not mute:
            print a
    if not mute:
        print "p={},i={},j={},a={}".format(a[lo], i, j, a)
    _partition(a, lo, j-1)
    _partition(a, j+1, hi)


def run_tests():
    test_knuth_shuffle()
    l = range(5)
    for i in range(10):
        quicksort(l)
        if not l == range(5):
            print "False assertion, l={}".format(l)
            assert False
    print "All tests passed"


if __name__ == "__main__":
    run_tests()
