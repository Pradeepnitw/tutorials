package leetcode.feb2015;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        ArrayList<Integer> currLevel = new ArrayList<Integer>();
        LinkedList<TreeNode> next = new LinkedList<TreeNode>();
        
        while (!queue.isEmpty()) {
            TreeNode n = queue.removeFirst();
            currLevel.add(n.val);
            if (n.left != null)
                next.addLast(n.left);
            if (n.right != null)
                next.addLast(n.right);
                
            // Change to next level
            if (queue.isEmpty()) {
                queue = next;
                next = new LinkedList<TreeNode>();
                res.add(currLevel);
                currLevel = new ArrayList<Integer>();
            }
        }
        
        return res;
    }
    
    public void testThis() {
    	TreeNode root = new TreeNode(3);
    	root.left = new TreeNode(9);
    	root.right = new TreeNode(20);
    	root.right.left = new TreeNode(15);
    	root.right.right = new TreeNode(7);
    	this.levelOrder(root);
    }
    
    public static void main(String[] arg) {
    	new BinaryTreeLevelOrderTraversal().testThis();
    }

}
