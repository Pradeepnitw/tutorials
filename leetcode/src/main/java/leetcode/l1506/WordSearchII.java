package leetcode.l1506;

import java.util.*;

public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        HashSet<String> res = new HashSet<String>();
        
        for (String s: words) {
            trie.insert(s);
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int ii = 0; ii < board.length; ii++) {
            for (int jj = 0; jj < board[ii].length; jj++) {
                dfs(board, trie, visited, res, ii, jj, trie.root, "");
            }
        }

        return new ArrayList<String>(res);
    }
    
    private void dfs(char[][] board, Trie trie, boolean[][] visited, Set<String> res, int i, int j, TrieNode parent, String w) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (visited[i][j]) return;
        
        TrieNode current = parent.children[board[i][j] - 'a'];
        if (current == null) return;
        
        String ww = w + board[i][j];
        if (current.isWord) {
            res.add(ww);
        }
        
        visited[i][j] = true;
        dfs(board, trie, visited, res, i + 1, j, current, ww);
        dfs(board, trie, visited, res, i - 1, j, current, ww);
        dfs(board, trie, visited, res, i, j + 1, current, ww);
        dfs(board, trie, visited, res, i, j - 1, current, ww);
        visited[i][j] = false;
        return;
    }
    
    public class TrieNode {
        public TrieNode[] children;
        public boolean isWord;
        // Initialize your data structure here.
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public class Trie {
        public TrieNode root;
    
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
        public TrieNode search(String word) {
            TrieNode n = root;
            int i = 0;
            while (i < word.length() && n.children[word.charAt(i) - 'a'] != null) {
                n = n.children[word.charAt(i) - 'a'];
                i++;
            }
            
            if (i == word.length()) return n;
            return null;
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
}