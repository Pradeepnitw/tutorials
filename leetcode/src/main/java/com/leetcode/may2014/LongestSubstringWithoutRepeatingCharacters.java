package com.leetcode.may2014;

public class LongestSubstringWithoutRepeatingCharacters {
	   public int lengthOfLongestSubstring(String s) {
	        if (s.length() == 0 ) return 0;
	        int p = 0; //starting point of non-duplicate string
	        int Min = 0;
	        int Max = 0;
	        
	        for (int i=1; i<s.length() ; i++) {
	            p = updateIndex(s, p, i);
	            
	            if ( (i-p) > (Max - Min) ) {
	                Max = i;
	                Min = p;
	            }
	        }
	        return Max-Min+1;
	        
	    }
	    
	    private int updateIndex(String s, int startIndex, int endIndex) {
	        if (endIndex <= startIndex) return startIndex;
	        
	        for (int i = endIndex-1; i >= startIndex ; i--) {
	            if (s.charAt(i) == s.charAt(endIndex) ) {
	                return i+1;
	            }
	        }
	        return startIndex;
	    }
}
