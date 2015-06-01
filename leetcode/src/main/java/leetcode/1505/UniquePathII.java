package leetcode.feb2015;

public class UniquePathII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return -1;
        if (obstacleGrid[0][0] == 1) return 0;
        int[][] cache = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[0].length; j++)
                cache[i][j] = -1;
        
        return recurse(obstacleGrid, cache, 0, 0);
    }
    
    private int recurse(int[][] g,int[][] cache, int i, int j) {
        if (i == g.length - 1 && j == g[0].length - 1) {
            if (g[i][j] == 1) return 0;
            else return 1;
        }
        
        if (cache[i][j] != -1) return cache[i][j];
        
        int path = 0;
        if (i < g.length - 1 && g[i+1][j] == 0) 
            path += recurse(g, cache, i+1, j);
        if (j < g[0].length - 1 && g[i][j+1] == 0)
            path += recurse(g, cache, i , j+1);
            
        cache[i][j] = path;
        return path;
    }
}
