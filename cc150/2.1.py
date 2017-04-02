
"""
Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem is a temporary buffer is not allowed
"""
class Node(object):
    def __init__(self, var=None, next=None):
        self.var = var
        self.next = next

def rm_duplicates(head):
    values = {head.var: True}
    while(head.next):
        if head.next.var in values:
            # rm this element by skipping it
            head.next = head.next.next
        else:
            values[head.next.var] = True
        head = head.next

def print_nodes(head):
    print "============="
    print head.var
    while(head.next):
        head = head.next
        print head.var
    print "====end======"

if __name__ == "__main__":
    values = ["a", "b", "a", "d"]
    idx = len(values) - 1
    head = Node(var=values[idx], next=None)
    idx -= 1
    while(idx >= 0):
        new_node = Node(var=values[idx], next=head)
        head = new_node
        idx -= 1
    print_nodes(head)
    rm_duplicates(head)
    print_nodes(head)
