package leetcode.may2014;

public class MaximalRectangleRecursive {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null) return 0;
        else if(matrix.length == 0) return 0;
        else if(matrix[0].length == 0) return 0;
        int[][] ones = new int[matrix.length][matrix[0].length];
        
        // Preprocess the matrix and store it in ones
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
        
        for (int j=matrix[0].length-1; j>=0; j--) {
        	maxArea = Math.max(getMax(ones, j, 0, matrix.length-1), maxArea);
        }
        return maxArea;
    }
    
    private int getMax(int[][] ones, int col, int lo, int hi) {
    	if (lo == hi) return ones[lo][col];
    	if (lo > hi) return 0;
    	int min = Integer.MAX_VALUE;
    	int minIndex = -1;
    	
    	for (int i=lo; i<=hi; i++) {
    		if (min > ones[i][col]) {
    			min = ones[i][col];
    			minIndex = i;
    		}
    	}
    	
    	int max = Math.max(getMax(ones, col, lo, minIndex-1), getMax(ones, col, minIndex+1, hi));
    	max = Math.max(max, min*(hi-lo+1));
    	//System.out.println("Max for col " + col + " lo "+lo+" hi "+hi+" is "+max);
    	return max;
    }
}
