package leetcode.l1506;

public class ImplementTrie {
	class TrieNode {
	    public TrieNode[] children;
	    public boolean isWord;
	    // Initialize your data structure here.
	    public TrieNode() {
	        children = new TrieNode[26];
	    }
	}

	public class Trie {
	    private TrieNode root;

	    public Trie() {
	        root = new TrieNode();
	    }

	    // Inserts a word into the trie.
	    public void insert(String word) {
	        TrieNode n = root;
	        int i = 0;
	        while (i < word.length() && n.children[word.charAt(i) - 'a'] != null) {
	            n = n.children[word.charAt(i) - 'a'];
	            i++;
	        }
	        
	        while (i < word.length()) {
	            TrieNode nn = n.children[word.charAt(i) - 'a'] = new TrieNode();
	            n = nn;
	            i++;
	        }
	        
	        n.isWord = true;
	        
	    }

	    // Returns if the word is in the trie.
	    public boolean search(String word) {
	        TrieNode n = root;
	        int i = 0;
	        while (i < word.length() && n.children[word.charAt(i) - 'a'] != null) {
	            n = n.children[word.charAt(i) - 'a'];
	            i++;
	        }
	        
	        if (i == word.length() && n.isWord) return true;
	        return false;
	    }

	    // Returns if there is any word in the trie
	    // that starts with the given prefix.
	    public boolean startsWith(String prefix) {
	        TrieNode n = root;
	        int i = 0;
	        while (i < prefix.length() && n.children[prefix.charAt(i) - 'a'] != null) {
	            n = n.children[prefix.charAt(i) - 'a'];
	            i++;
	        }
	        
	        if (i == prefix.length()) return true;
	        return false;
	    }
	}

	// Your Trie object will be instantiated and called as such:
	// Trie trie = new Trie();
	// trie.insert("somestring");
	// trie.search("key");
}
