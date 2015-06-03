package leetcode.l1506;


public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode newHead = recurse(head);
        head.next = null;
        return newHead;
    }
    
    private ListNode recurse(ListNode current) {
        if (current.next == null) {
            return current;
        }
        
        ListNode tail = recurse(current.next);
        current.next.next = current;
        return tail;
    }

}
