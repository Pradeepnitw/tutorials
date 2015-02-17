package leetcode.feb2015;

import java.util.LinkedList;

public class SymmetricTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	/*
	 * Recursive solution
	 */
    public boolean isSymmetricRecursive(TreeNode root) {
    	if (root == null) return true;
        return isSymmerticRecursive(root.left, root.right);
    }
    
    public boolean isSymmerticRecursive(TreeNode leftTwin, TreeNode rightTwin) {
    	if (leftTwin == null && rightTwin == null) return true;
    	if (leftTwin == null || rightTwin == null) return false;
    	if (leftTwin.val != rightTwin.val) return false;
    	
    	if(!isSymmerticRecursive(leftTwin.left, rightTwin.right))
    		return false;
    	if(!isSymmerticRecursive(leftTwin.right, rightTwin.left))
    		return false;
    	
    	return true;
    }
    
    /*
     * Iterative Solution
     */

    public boolean isSymmetric(TreeNode root) {
    	if (root == null) return true;
    	if (root.left == null && root.right == null) return true;
    	if (root.left == null && root.right != null) return false;
    	if (root.left != null && root.right == null) return false;
    	
    	LinkedList<TreeNode> leftQ = new LinkedList<TreeNode>();
    	LinkedList<TreeNode> rightQ = new LinkedList<TreeNode>();
    	leftQ.add(root.left);
    	rightQ.add(root.right);
    	
    	while(!leftQ.isEmpty()) {
    		TreeNode leftTwin = leftQ.pop();
    		TreeNode rightTwin = rightQ.pop();
    		
    		if (leftTwin.val != rightTwin.val) return false;
    		
    		if (leftTwin.left == null && rightTwin.right == null) {
    			// Do nothing
    		} else if (leftTwin.left != null && rightTwin.right != null) {
    			leftQ.add(leftTwin.left);
    			rightQ.add(rightTwin.right);
    		} else return false;
    		
    		if (leftTwin.right == null && rightTwin.left == null) {
    			// Do nothing
    		} else if (leftTwin.right != null && rightTwin.left != null) {
    			leftQ.add(leftTwin.right);
    			rightQ.add(rightTwin.left);
    		} else return false;
    	}
    	
    	return true;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
