package com.leetcode.may2014;

import java.util.*;

public class WordBreakII {
	   HashMap<String, ArrayList<String>> dynamic;
	    
	    public ArrayList<String> wordBreak(String s, Set<String> dict) {
	        dynamic = new HashMap<String, ArrayList<String>>();
	        return recursive(s, dict);
	    }
	    
	    private ArrayList<String> recursive(String s, Set<String> dict) {
	        ArrayList<String> list = new ArrayList<String>();
	        if (dynamic.containsKey(s))
	            return dynamic.get(s);
	        
	        if (s.length() == 0) return list;
	        if (dict.contains(s)) {
	            list.add(s);
	        }
	        
	        for (int i=1; i<s.length(); i++) {
	            if (dict.contains(s.substring(0, i))) {
	                ArrayList<String> subList = recursive(s.substring(i, s.length()), dict);
	                for (String solution: subList) {
	                    solution = s.substring(0,i) + " " + solution;
	                    list.add(solution);
	                }
	            }
	        }
	        dynamic.put(s, list);
	        return list;
	    }
}
