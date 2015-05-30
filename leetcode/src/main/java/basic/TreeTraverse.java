package basic;

import java.util.LinkedList;

public class TreeTraverse {
	public static class Tree {
		Tree left;
		Tree right;
		char val;
		
		public Tree(char v) {
			val = v;
		}
	}
	
	public static void preorder(Tree n) {
		if (n == null) return;
		System.out.print(n.val);
		preorder(n.left);
		preorder(n.right);
	}
	
	public static void preorderIter(Tree n) {
		LinkedList<Tree> stack = new LinkedList<Tree>();
		//TODO
		
	}
	
	public static void inorder(Tree n) {
		if (n == null) return;
		inorder(n.left);
		System.out.print(n.val);
		inorder(n.right);
	}
	
	public static void postorder(Tree n) {
		if (n == null) return;
		postorder(n.left);
		postorder(n.right);
		System.out.print(n.val);
	}
	

	
	public static void main(String[] args) {
		//      F
		//    /   \
		//   B     G
		//  / \     \
		// A   D     I
		//    / \   /
		//   C   E H
		
		Tree root = new Tree('F');
		root.left = new Tree('B');
		root.right = new Tree('G');
		root.left.left = new Tree('A');
		root.left.right = new Tree('D');
		root.right.right = new Tree('I');
		root.left.right.left = new Tree('C');
		root.left.right.right = new Tree('E');
		root.right.right.left = new Tree('H');
		
		System.out.println("Pre-order\nFBADCEGIH");
		TreeTraverse.preorder(root);
		System.out.println("\nIn-order\nABCDEFGHI");
		TreeTraverse.inorder(root);
		System.out.println("\nPost-order\nACEDBHIGF");
		TreeTraverse.postorder(root);
		System.out.println();
		
	}
}
