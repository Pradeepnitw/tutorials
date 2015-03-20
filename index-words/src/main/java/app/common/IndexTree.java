package app.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class IndexTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	public class TreeNode<E> implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private char key;
		private ArrayList<E> wordList;
		private Hashtable<Character, TreeNode<E>> children;
		
		public TreeNode(char key, E e) {
			this(key);
			
			wordList.add(e);
		}
		
		public TreeNode(char key) {
			this.key = key;
			
			wordList = new ArrayList<E>();
			children = new Hashtable<Character, TreeNode<E>>();
		}
		
		protected void addChild(TreeNode<E> child) throws NullPointerException, IllegalArgumentException {
			if (child == null)
				throw new java.lang.NullPointerException();
			
			if (children.contains(child.getKey()))
				throw new java.lang.IllegalArgumentException("Child already exist");
			
			children.put(child.getKey(), child);
		}
		
		protected void addWord(E e) {
			if (e == null)
				throw new java.lang.NullPointerException();
			wordList.add(e);
		}
		
		public String toString() {
			// Test purpose
			StringBuilder sb = new StringBuilder();
			sb.append("Key=" + this.key + " Words={");
			for (E e: wordList) {
				sb.append(e.toString() + " ");
			}
			sb.append("}");
			return sb.toString();
		}
		
		// Setters and Getters beyond this point
		protected char getKey() {
			return this.key;
		}
		
		protected Hashtable<Character, TreeNode<E>> getChildrenList() {
			return children;
		}
		
		protected ArrayList<E> getWordList() {
			return wordList;
		}		
		
		protected TreeNode<E> getChild(char key) {
			if (children.containsKey(key))
				return children.get(key);
			else return null;
		}
	}
	
	// The children of root
	private Hashtable<Character, TreeNode<WordTuple>> children;
	private int size = 0;
	
	public IndexTree() {
		children = new Hashtable<Character, TreeNode<WordTuple>>();
	}
	
	public void insertWord(String word, int score) {
		if (word == null || word.length() == 0) {
			return;
		}
		
		String trimmedWord = word.trim();
		String[] keywords = trimmedWord.toLowerCase().split("_");
		// e.g: if word is "red_tulip"
		// keywords[] = {"red","tulip"}
		
		WordTuple currentWord = new WordTuple(trimmedWord, score);
		
		for (String s : keywords) {
			if (s.length() == 0) continue;
			s = s.trim();
			
			// Get the first character of this keyword
			// e.g: 'r' in "red"
			TreeNode<WordTuple> node = children.get(s.charAt(0));
			
			// if this tree don't have a children start with 'r',
			// then create one.
			if (node == null) {
				node = new TreeNode<WordTuple>(s.charAt(0));
				children.put(s.charAt(0), node);
			}
			
			for (int i=1; i<s.length(); i++) {
				if (node.getChild(s.charAt(i)) == null) {
					TreeNode<WordTuple> nextNode = new TreeNode<WordTuple>(s.charAt(i));
					node.addChild(nextNode);
					node = nextNode;
				} else {
					node = node.getChild(s.charAt(i));
				}
			}
			
			// Now node is at the last character
			// e.g. 'd' in "red"
			// Add current WordTuple to this node's nameList
			// Notice that all the keyword's last node will contain the same WordTuple in memory
			node.addWord(currentWord);
		}
		size++;
	}
	
	public WordTuple[] queryByComparator(String queryString, int num, Comparator<WordTuple> comparator) {
		if (num <= 0)
			return null;
		WordTuple[] words = new WordTuple[num];
		
		TreeSet<WordTuple> heap = new TreeSet<WordTuple>(comparator);
		
		LinkedList<TreeNode<WordTuple>> queue = new LinkedList<TreeNode<WordTuple>>();
		
		//bfs
		if (queryString == null || queryString == "") {
			// Count all the words in this tree and return the top [num] of them
			for (TreeNode<WordTuple> n : children.values()) {
				queue.add(n);
				for (WordTuple w : n.wordList) {
					if (!heap.contains(w)) {
						heap.add(w);
						if (heap.size() > num)
							heap.pollLast();
					}
				}
			}
		} else {
			TreeNode<WordTuple> lastNode = getNodeByCharacterArray(queryString.toCharArray());
			if (lastNode == null) 
				return words;
			queue.add(lastNode);
			for (WordTuple w : lastNode.wordList) {
				if (!heap.contains(w)) {
					heap.add(w);
					if (heap.size() > num)
						heap.pollLast();
				}
			}
		}
		
		while (!queue.isEmpty()) {
			queue.addAll(queue.peek().children.values());
			for (WordTuple w : queue.peek().wordList) {
				if (!heap.contains(w)) {
					heap.add(w);
					if (heap.size() > num)
						heap.pollLast();
				}
			}
			queue.pop();
		}
		
		for (int i = 0; i < num; i++) {
			words[i] = heap.pollFirst();
		}
		
		return words;
	}
	
	/*
	 * Return the node that matches the keys[] from root down.
	 */
	public TreeNode<WordTuple> getNodeByCharacterArray(char[] keys) {
		if (keys == null || keys.length == 0)
			return null;
		int i = 0;
		TreeNode<WordTuple> node = children.get(keys[i++]);
		while (node != null && i < keys.length) {
			node = node.getChild(keys[i++]);
		}
		return node;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// bfs by level
		LinkedList<TreeNode<WordTuple>> queueA = new LinkedList<TreeNode<WordTuple>>();
		LinkedList<TreeNode<WordTuple>> queueB = new LinkedList<TreeNode<WordTuple>>();
		
		for (char key: children.keySet()) {
			queueA.add(children.get(key));
		}
		
		while (!queueA.isEmpty()) {
			Iterator<TreeNode<WordTuple>> iter = queueA.iterator();
			while (iter.hasNext()) {
				TreeNode<WordTuple> n = iter.next();
				queueB.addAll(n.getChildrenList().values());
				sb.append(n.toString() + " ");
			}
			sb.append("\n");
			queueA = queueB;
			queueB = new LinkedList<TreeNode<WordTuple>>();
			
		}
		
		return sb.toString();
	}
	
	public int size() {
		return this.size;
	}
	
	// Setters and Getters beyond this point
	protected Hashtable<Character, TreeNode<WordTuple>> getChildren() {
		return this.children;
	}
}
