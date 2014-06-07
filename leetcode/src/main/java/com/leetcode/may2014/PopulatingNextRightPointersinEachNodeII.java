package com.leetcode.may2014;

import java.util.*;

public class PopulatingNextRightPointersinEachNodeII {
	/**
	 * Definition for binary tree with next pointer.
	 */
	public class TreeLinkNode {
	     int val;
	     TreeLinkNode left, right, next;
	     TreeLinkNode(int x) { val = x; }
	}
	 
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        LinkedList<TreeLinkNode> children = new LinkedList<TreeLinkNode>();
        children.add(root);
        connectChildren(children);
    }
    
    private void connectChildren(LinkedList<TreeLinkNode> children) {
        if (children == null) return;
        if (children.size() == 0) return;
        
        int i = 0;
        LinkedList<TreeLinkNode> nextGeneration = new LinkedList<TreeLinkNode>();
        while (i < children.size()-1) {
            TreeLinkNode node = children.get(i);
            if (node.left != null)
                nextGeneration.add(node.left);
            if (node.right != null)
                nextGeneration.add(node.right);
            node.next = children.get(i+1);
            i++;
        }
        
        TreeLinkNode node = children.getLast();
            if (node.left != null)
                nextGeneration.add(node.left);
            if (node.right != null)
                nextGeneration.add(node.right);
        node.next = null;
        connectChildren(nextGeneration);
    }
}
