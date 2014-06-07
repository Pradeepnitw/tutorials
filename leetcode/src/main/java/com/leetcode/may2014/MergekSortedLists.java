package com.leetcode.may2014;

import java.util.*;

public class MergekSortedLists {
	/**
	 * Definition for list node.
	 */
	 public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	  }
	
	 public ListNode mergeKLists(ArrayList<ListNode> lists) {
	        if (lists == null || lists.size() == 0) {
	            return null;
	        }
	        return mergeNodes(lists, 0, lists.size()-1);
	 }
	    
	 private ListNode mergeNodes(ArrayList<ListNode> lists, int s, int e) {
		    if (s == e) {
		        return lists.get(s);
		    } else if (s > e) {
		        return null;
		    } 
		    
		    ListNode list1 = lists.get(s);
		    ListNode list2 = lists.get(e);
		    
		    if (s < e-1) {
		        int split = s+(e-s)/2;
		        list1 = mergeNodes(lists, s, split);
		        list2 = mergeNodes(lists, split+1, e);
		    } 
		    
		    // Merge list when s=e-1 or we already merged lists within range into two lists
		    
		    ListNode head = null;
		    ListNode curr = null;
		    
		    if (list1 == null && list2 == null) {
		        return null;
		    } else if (list1 == null) {
		        head = list2;
		        curr = head;
		        list2 = list2.next;
		    } else if (list2 == null) {
		        head = list1;
		        curr = head;
		        list1 = list1.next;
		    } else {        
		        if (list1.val < list2.val) {
		            head = list1;
		            curr = head;
		            list1 = list1.next;
		        } else {
		            head = list2;
		            curr = head;
		            list2 = list2.next;
		        }
		    }
		    
		    while (list1 != null || list2 != null) {
		        if (list1 == null) {
		            curr.next = list2;
		            list2 = list2.next;
		        } else if (list2 == null) {
		            curr.next = list1;
		            list1 = list1.next;
		        } else if (list1.val < list2.val) {
		            curr.next = list1;
		            list1 = list1.next;
		        } else if (list2.val <= list1.val) {
		            curr.next = list2;
		            list2 = list2.next;
		        }
		        curr = curr.next;
		    }
		    return head;
		}
}
