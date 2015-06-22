package leetcode.l1506;

public class EditDistance {
    public int minDistance(String w1, String w2) {
        if (w1 == null || w2 == null) return 0;
        if (w1.length() == 0 || w2.length() == 0) return Math.max(w1.length(), w2.length());
        int[][] dp = new int[w1.length()+1][w2.length()+1];
        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = Math.max(i, j);
                } else {
                    if (w1.charAt(i-1) == w2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                    }
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}