
"""
    Implement a function to check if a linked list is a palindrome.
"""
class Node(object):
    def __init__(self, val, next):
        self.val = val
        self.next = next

    def __len__(self):
        current = self
        len = 1
        while current.next:
            len += 1
            current = current.next
        return len

    def __str__(self):
        s = ""
        ptr = self
        while ptr:
            s = "{} {}".format(s, ptr.val)
            ptr = ptr.next


def construct_palindrome(l):
    """ Construct a palindrome from a list """
    if not l:
        return
    idx = len(l) - 1
    head = None
    while idx >= 0:
        head = Node(l[idx], head)
        idx -= 1
    return head


def test_construct_palindrome():
    l1 = [0, 1, 2, 1, 0]
    h1 = construct_palindrome(l1)
    assert len(h1) == len(l1)
    for val in l1:
        assert h1.val == val
        h1 = h1.next
    assert h1 is None
    assert construct_palindrome([]) is None
    assert construct_palindrome(None) is None


def _split(head):
    fast = head
    slow = head
    while fast and fast.next and fast.next.next:
        fast = fast.next.next
        slow = slow.next
    head2 = slow.next
    slow.next = None
    return head, head2


def test__split():
    l1 = [0, 1, 2, 1, 0]
    h1 = construct_palindrome(l1)
    sl1, sl2 = _split(h1)
    assert len(sl1) == 3
    assert len(sl2) == 2

def _reverse(head):
    new_head = None
    while head:
        temp = head.next
        head.next = new_head
        new_head = head
        head = temp
    return new_head

def test__reverse():
    h1 = construct_palindrome([1, 2, 3])
    h2 = _reverse(h1)
    for n in [3, 2, 1]:
        assert h2.val == n
        h2 = h2.next

def is_palindrom(head):
    if not head:
        return False
    elif not head.next:
        return True
    # split list in half
    first_half, second_half = _split(head)
    # reverse second half
    second_half = _reverse(second_half)
    # compare two lists
    while second_half:
        if first_half.val != second_half.val:
            return False
        first_half = first_half.next
        second_half = second_half.next
    return True

def test_is_palindrom():
    assert is_palindrom(construct_palindrome([0, 1, 2, 1, 0]))
    assert is_palindrom(construct_palindrome([0, 1, 2, 2, 1, 0]))
    assert is_palindrom(construct_palindrome([0]))
    assert not is_palindrom(construct_palindrome([0, 1, 2, 0]))

def run_tests():
    test_construct_palindrome()
    test__split()
    test__reverse()
    test_is_palindrom()
    print "All tests passed"
    

if __name__ == "__main__":
    run_tests()
    
