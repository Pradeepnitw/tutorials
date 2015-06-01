package leetcode.feb2015;

/*
 * https://oj.leetcode.com/problems/unique-paths/
 */
public class UniquePath {
	
    public int uniquePaths(int m, int n) {
    	int[][] dp = new int[m][n];
    	
    	dp[m-1][n-1] = 1;
    	
    	// Right most column
    	for (int i = m-2; i >= 0; i--) {
    		dp[i][n-1] = 1;
    	}
    	
    	for (int j = n-2; j >= 0; j--) {
    		dp[m-1][j] = 1;
    	}
    	
    	for (int i = m-2; i >= 0; i--) {
    		for (int j = n-2; j >= 0; j--) {
    			dp[i][j] = dp[i+1][j] + dp[i][j+1];
    		}
    	}
    	
    	return dp[0][0];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
