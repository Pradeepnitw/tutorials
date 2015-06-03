package leetcode.l1405;

public class BinaryTreeMaximumPathSum {
	/**
	 * Definition for binary tree
	 */
	public class TreeNode {
		int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
    public static int GLOBAL_MAX = 0;
    public static int SUBTREE_MAX = 1;
    
    public int maxPathSum(TreeNode root) {
        int[] tracker = new int[2];
        tracker[GLOBAL_MAX] = Integer.MIN_VALUE;
        return maxChildPath(root, tracker)[GLOBAL_MAX];
    }
    
    // tracker = {GLOBAL_MAX, SUBTREE_MAX}
    private int[] maxChildPath(TreeNode node, int[] tracker) {
        if (tracker.length != 2) return null;
        
        int r = 0;
        if (node.right != null) 
            r = maxChildPath(node.right, tracker)[SUBTREE_MAX];
            r = r<0?0:r;
        int l = 0;
        if (node.left != null) 
            l = maxChildPath(node.left, tracker)[SUBTREE_MAX];
            l = l<0?0:l;
        
        
        // This subtree's total val
        int sumOfTreeVal = r + l + node.val; 
        tracker[GLOBAL_MAX] = Math.max(sumOfTreeVal, tracker[GLOBAL_MAX]);
        
        // Compare the left path val with right path val
        int maxBranchVal = Math.max(l,r) + node.val;
        tracker[SUBTREE_MAX] = maxBranchVal;
        
        return tracker;
    }
	
}
