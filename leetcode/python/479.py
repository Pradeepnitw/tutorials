from math import log10, pow


length_of = lambda x: int(log10(x))
digit_at = lambda n, d: (n/int(pow(10, d)))%10

def largest_palindrome(n):
    max_val = int(pow(10,n)) - 1
    lo = max_val  # lower bound of each iteration
    max_palindrome = 0
    while lo > 0:
        m = max_val
        if m * lo < max_palindrome:
            return max_palindrome % 1337
        while m >= lo:
            if is_palindrome(m * lo):
                max_palindrome = m * lo
            m -= 1
        lo -= 1


def is_palindrome(n):
    d = length_of(n)
    i = 0
    while i <= (d - i):
        if not digit_at(n, i) == digit_at(n, d-i):
            return False
        i += 1
    return True
