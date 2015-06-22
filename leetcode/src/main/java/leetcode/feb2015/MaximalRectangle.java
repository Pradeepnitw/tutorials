package leetcode.feb2015;

import java.util.LinkedList;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;
        int[][] aux = new int[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == '0') aux[i][0] = -1;
            else if (matrix[i][0] == '1') aux[i][0] = 0;
            else throw new java.lang.IllegalArgumentException();
            
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') aux[i][j] = -1;
                else if (matrix[i][j] == '1') {
                    if (matrix[i][j-1] == '1') aux[i][j] = aux[i][j-1];
                    else if (matrix[i][j-1] == '0') aux[i][j] = j;
                } else throw new java.lang.IllegalArgumentException();
            }
        }
        
        int res = 0;
        for (int i = 0; i < aux.length; i++) {
            res = Math.max(res, maxAreaOfHistogram(aux[i], i));
        }
        return res;
    }
    
    private int maxAreaOfHistogram(int[] a, int row) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] == -1) a[i] = 0;
            else {
                a[i] = row+1-a[i];
            }
        }
        
        
        stack.push(0);
        int i = 1;
        while (i < a.length) {
            if (a[i] >= a[stack.peek()]) {
                stack.push(a[i]);
            } else {
            }
            
            stack.push(i);
            
            i++;
        }
        
        return -1;
    }
}
