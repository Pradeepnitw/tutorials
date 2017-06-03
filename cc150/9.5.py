
"""
    Write a method to compute all permutations of a string
"""

def perms(s):
    if not s:
        return
    if len(s) == 1:
        return [s]
    c = s[0]
    prev_perms = perms(s[1:])  # list of permutations of the string without first char
    next_perms = []  # list of permutations with the current char
    for ss in prev_perms:
        for i in range(len(ss)+1):
            next_perms.append(ss[:i] + s[0] + ss[i:])
    return next_perms


def run_tests():
    ans = ['ABC', 'BAC', 'BCA', 'ACB', 'CAB', 'CBA']
    res = perms("ABC")
    for s in ans:
        assert s in res
    assert len(ans) == len(res)
    print "All tests passed"

if __name__ == "__main__":
    run_tests()
