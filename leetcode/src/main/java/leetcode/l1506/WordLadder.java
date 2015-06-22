package leetcode.l1506;

import java.util.*;


public class WordLadder {
	public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
		if (beginWord == null || endWord == null || wordDict == null) return 0;
		if (beginWord.equals(endWord)) return 0;
		
		HashSet<String> dict = new HashSet<String>();
		for (String s : wordDict) {
			dict.add(s);
		}
		dict.add(endWord);
		
		LinkedList<String> wordQ = new LinkedList<String>();
		LinkedList<Integer> distQ = new LinkedList<Integer>();
		wordQ.add(beginWord);
		distQ.add(1);
		
		while (!wordQ.isEmpty()) {
			String w = wordQ.removeFirst();
			int i = distQ.removeFirst();
			if (w.equals(endWord)) return i;
			List<String> nei = this.getNeighbours(w, dict);
			for (String s: nei) {
				wordQ.add(s);
				distQ.add(i+1);
			}
		}
		
		return 0;
	}
    
    
    private List<String> getNeighbours(String word, HashSet<String> wordDict) {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < word.length(); i++) {
			
			char c = 'a';
			while (c <= 'z') {
				char[] perm = word.toCharArray();
				if (perm[i] == c) c++;
				perm[i] = c;
				
				String p = String.valueOf(perm);

				if (wordDict.contains(p)) {
					res.add(p);
					wordDict.remove(p);
				}
				c++;
			}
		}
		return res;
	}
}