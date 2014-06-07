package com.leetcode.may2014;

public class RomantoInteger {
	   public int romanToInt(String s) {
	        if (s.length() >= 20) return -1;
	        int[] num = new int[s.length()];
	        for (int i=0; i<s.length(); i++) {
	            if (s.charAt(i) == 'I') {
	                if (i == s.length()-1) {
	                    num[i] = 1;
	                } else if (s.charAt(i+1) == 'V') {         // IV = 4
	                    num[i] = 4;
	                    num[++i] = 0;
	                } else if (s.charAt(i+1) == 'X') {  // IX = 9
	                    num[i] = 9;
	                    num[++i] = 0;
	                } else {                            // V = 1
	                    num[i] = 1;
	                }
	            } else if (s.charAt(i) == 'V') {        // V = 5
	                    num[i] = 5;
	            } else if (s.charAt(i) == 'X') {        
	                if (i == s.length()-1) {
	                    num[i] = 10;
	                } else if (s.charAt(i+1) == 'L') {         // XL = 40
	                    num[i] = 40;
	                    num[++i] = 0;
	                } else if (s.charAt(i+1) == 'C') {  // XC = 90
	                    num[i] = 90;
	                    num[++i] = 0;
	                } else {                            // X = 10
	                    num[i] = 10;
	                }
	            } else if (s.charAt(i) == 'L') {        // L = 50
	                    num[i] = 50;
	            } else if (s.charAt(i) == 'C') {        
	                if (i == s.length()-1) {
	                    num[i] = 100;
	                } else if (s.charAt(i+1) == 'D') {         // CD = 400
	                    num[i] = 400;
	                    num[++i] = 0;
	                } else if (s.charAt(i+1) == 'M') {  // CM = 900
	                    num[i] = 900;
	                    num[++i] = 0;
	                } else {                            // C = 100
	                    num[i] = 100;
	                }
	            } else if (s.charAt(i) == 'D') {        // D = 500
	                    num[i] = 500;
	            } else if (s.charAt(i) == 'M') {        // M = 1000
	                    num[i] = 1000;
	            } else {
	                return -1;
	            }
	        }
	        // Summ up all num then return
	        // If bigger than 3999 return -1
	        int sum = 0;
	        for (int i: num) {
	            sum += i;
	        }
	        if (sum > 3999)
	            return -1;
	        else 
	            return sum;
	    }
}
