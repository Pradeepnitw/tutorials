package com.leetcode.may2014;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
		int max = 0;
		// Array initialization
		for (int i=0; i<dp.length ; i++) {
			dp[i] = 0;
		}		

		// Main loop
		for (int i=1; i<dp.length ; i++) {
			if (s.charAt(i) == ')' ) {
                int j = i-1-dp[i-1];
                if ( j>=0 ) {
                	if (s.charAt(j) == '(') {
                		dp[i] = dp[i-1] + 2;
                		int k = j-1;
    	                if ( k >= 0 ) {
    	                	dp[i] += dp[k];
    	                }
                	}
	                                
                	
                }  
                if (dp[i] > max)
                	max = dp[i];
            }
		}
		return max;
    }
}