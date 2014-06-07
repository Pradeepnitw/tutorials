package com.leetcode.may2014;

import java.util.*;

public class LargestRectangleInHistogram {
	   public int largestRectangleArea(int[] height) {
	        return getMax(height, 0, height.length-1);
	    }
	    
	    private int getMax(int[] ones, int lo, int hi) {
	    	LinkedList<Integer> valStack = new LinkedList<Integer>();
	    	LinkedList<Integer> idxStack = new LinkedList<Integer>();
	    	
	    	
	    	int i = lo;
	    	int max = 0;
	    	while (i <= hi) {
	    		while (!valStack.isEmpty() && ones[i] < valStack.peek()) {
	    			int val = valStack.pop();
	    			int idx = idxStack.pop();
	    			
	    			if (valStack.isEmpty())
	    				max = Math.max(max, val*i);
	    			else
	    				max = Math.max(max, val*(i-idxStack.peek()-1));
	    			//max = Math.max(max, ones[i][col]*(i-idx+1));
	    		}
	    		valStack.push(ones[i]);
	    		idxStack.push(i);
	    		i++;
	    	}
	    	
	    	while (!valStack.isEmpty()) {
	    		// i=hi+1
	    		
	    		int val = valStack.pop();
				int idx = idxStack.pop();
				
				if (valStack.isEmpty())
	    			max = Math.max(max, val*i);
	    		else
	    			max = Math.max(max, val*(i-idxStack.peek()-1));
	    	}
	    	
	    	return max;
	    }
}
