package leetcode.may2014;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if(n == 1 || n == 0) 
            return;
        
        for (int m=0; m<=(n-1)/2; m++) {
            for (int d=0; d<=n-2*m-2; d++) {
                int temp = matrix[m][m+d];                   // Take top one out
                matrix[m][m+d] = matrix[n-1-m-d][m];         // top <= left
                matrix[n-1-m-d][m] = matrix[n-1-m][n-1-m-d]; // left <= bottom
                matrix[n-1-m][n-1-m-d] = matrix[m+d][n-1-m]; // bottom <= right
                matrix[m+d][n-1-m] = temp;                   // right <= top
            }
        }
    }
}
