package com.leetcode.may2014;

import java.util.*;

public class CopyListwithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        
        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode p = newHead;
        RandomListNode q = head;
        map.put(head, p);
        
        // Create link for next
        while (q.next != null) {
            p.next = new RandomListNode(q.next.label);
            p = p.next;
            q = q.next;
            map.put(q, p);
        }
        
        // Create link for random
        for (RandomListNode key: map.keySet()) {
            map.get(key).random = map.get(key.random);
        }
        
        return newHead;
    }
    
    
    /**
     * Definition for singly-linked list with a random pointer.
     */
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
     
    
}
