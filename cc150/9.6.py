
"""
    Implement an algorithm to print all valid (i.e., properly opened and
    closed) combinations of n-pairs of parentheses.
"""

def print_parentheses(n, s="", num_left=0, num_right=0):
    res = []
    if num_left >= n and num_right >= n:
        return [s]
    if num_left < n:
        res.extend(print_parentheses(n, s + "(", num_left + 1, num_right))
    if num_left > num_right:
        res.extend(print_parentheses(n, s + ")", num_left, num_right + 1))
    return res


def run_tests():
    ans = ['(((())))', '((()()))', '((())())', '((()))()', '(()(()))',
            '(()()())', '(()())()', '(())(())', '(())()()', '()((()))',
            '()(()())', '()(())()', '()()(())', '()()()()']
    res = print_parentheses(4)
    assert len(res) == len(ans)
    for e in ans:
        assert e in res
    print "All tests passed"


if __name__ == "__main__":
    run_tests()
