package app.common;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class IndexTree {
	public class WordTuple {
		private String name;
		private int score;
		
		public WordTuple(String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		protected String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		protected int getScore() {
			return score;
		}
		protected void setScore(int score) {
			this.score = score;
		}
		public String toString() {
			return "[" + name + "-" + score + "]";
		}
	}
	
	public class TreeNode<E> {
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
		
		protected TreeNode<E> getChild(char key) {
			if (children.containsKey(key))
				return children.get(key);
			else return null;
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
		
		protected ArrayList<E> getWordList() {
			return wordList;
		}
		
		protected char getKey() {
			return this.key;
		}
		
		protected Hashtable<Character, TreeNode<E>> getChildrenList() {
			return children;
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
	}
	
	// The children of root
	private Hashtable<Character, TreeNode<WordTuple>> children;
	
	public IndexTree() {
		children = new Hashtable<Character, TreeNode<WordTuple>>();
	}
	
	public void insertWord(String word, int score) throws NullPointerException {
		if (word == null || word.length() == 0) {
			throw new java.lang.NullPointerException("Inserting empty word into IndexTree");
		}
		
		String trimmedWord = word.trim();
		String[] keywords = trimmedWord.toLowerCase().split("_");
		// e.g: if word is "red_tulip"
		// keywords[] = {"red","tulip"}
		
		WordTuple currentWord = new WordTuple(trimmedWord, score);
		
		for (String s : keywords) {
			if (s.length() == 0) continue;
			
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
	
	protected Hashtable<Character, TreeNode<WordTuple>> getChildren() {
		return this.children;
	}
}
