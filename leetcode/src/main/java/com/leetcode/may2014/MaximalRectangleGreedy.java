package com.leetcode.may2014;

import java.util.*;

public class MaximalRectangleGreedy {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;
        else if(matrix.length == 0) return 0;
        else if(matrix[0].length == 0) return 0;
        int[][] ones = new int[matrix.length][matrix[0].length];
        
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    ones[i][j] = 0;
                } else {
                    if (j == 0) {
                        ones[i][j] = 1;
                    } else {
                        ones[i][j] = ones[i][j-1] + 1;
                    }
                }
            }
        }
        
        int maxArea = 0;
        int start = -1;
        int end = -1;
        for (int j=0; j<matrix[0].length; j++) {
        	for (int i=0; i<matrix.length; i++) {
            	if (ones[i][j] == 0) {
            		if (start != -1) {
            			end = i;
            			maxArea = Math.max(getMax(ones, j, start, end), maxArea);
            			start = -1;
            			end = -1;
            		} 
            	}else {
            		if (start == -1) {
            			start = i;
            		}
            	}
            }
            if (start != -1 && end == -1) {
                maxArea = Math.max(getMax(ones, j, start, matrix.length-1), maxArea);
                start = -1;
                end = -1;
            }
        }
        

        
        return maxArea;
    }
    
    private int getMax(int[][] ones, int col, int lo, int hi) {
    	LinkedList<Integer> valStack = new LinkedList<Integer>();
    	LinkedList<Integer> idxStack = new LinkedList<Integer>();
    	
    	
    	int i = 0;
    	int max = 0;
    	while (i+lo <= hi) {
    		while (!valStack.isEmpty() && ones[i+lo][col] < valStack.peek()) {
    			int val = valStack.pop();
    			idxStack.pop();
    			
    			if (valStack.isEmpty())
    				max = Math.max(max, val*i);
    			else
    				max = Math.max(max, val*(i-idxStack.peek()-1));
    			//max = Math.max(max, ones[i][col]*(i-idx+1));
    		}
    		valStack.push(ones[i+lo][col]);
    		idxStack.push(i);
    		i++;
    	}
    	
    	while (!valStack.isEmpty()) {
    		// i=hi+1
    		
    		int val = valStack.pop();
			idxStack.pop();
			
			if (valStack.isEmpty())
				max = Math.max(max, val*i);
			else
				max = Math.max(max, val*(i-idxStack.peek()-1));
    	}
    	
    	return max;
    }
}
