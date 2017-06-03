
"""
    Write a method to return all subsets of a set.
"""

from collections import defaultdict

def subsets(s):
    result = [set()]
    for e in s:
        subs = []
        for subset in result:
            new_set = set(subset) # deepcopy
            new_set.add(e)
            subs.append(new_set)
        result.extend(subs)
    return result


def run_tests():
    res = subsets(set([1, 2, 3, 4]))
    ans = [set([]), set([1]), set([2]), set([1, 2]), set([3]), set([1, 3]),
            set([2, 3]), set([1, 2, 3]), set([4]), set([1, 4]), set([2, 4]),
            set([1, 2, 4]), set([3, 4]), set([1, 3, 4]), set([2, 3, 4]),
            set([1, 2, 3, 4])]
    for e in ans:
        assert e in res
    assert len(res) == len(ans)
    print "All tests passed"


if __name__ == "__main__":
    run_tests()
