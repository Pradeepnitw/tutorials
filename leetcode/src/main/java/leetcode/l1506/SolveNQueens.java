package leetcode.l1506;

import java.util.*;

public class SolveNQueens {
    public static void main(String[] arg) {
        new SolveNQueens().solveNQueens(2);
    }

    public List<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        
        solveNQueens(new char[n][n], new boolean[6][n], 0, 0, 0, res);
        
        return res;
    }
    
    private void solveNQueens(char[][] board, boolean[][] hasQ, int numOfQ, int i, int j, ArrayList<String[]> res) {
        int idx1D = i*board.length + j;
        if (idx1D == board.length*board.length) {
            if (numOfQ == board.length) {
                // Add board to res
                String[] b = new String[board.length];
                for (int k = 0; k < b.length; k++) {
                    b[k] = String.valueOf(board[k]);
                }
                res.add(b);
            }
            return;
        }
        
        
        boolean[][] h1 = hasQ.clone();
        char[][] b1 = placeQueen(board.clone(), h1, i, j);
        if (b1 != null) {
            solveNQueens(b1, h1, numOfQ+1, (idx1D + 1) / board.length, (idx1D + 1) % board.length, res);
        } 
        
        char[][] b2 = board.clone();
        b2[i][j] = '.';
        solveNQueens(b2, hasQ, numOfQ, (idx1D + 1) / board.length, (idx1D + 1) % board.length, res);
        
    }
    
    private int COL = 0; // h[0][0] - h[0][n-1]
    private int ROW = 1; // h[1][0] - h[1][n-1]
    private int FOR = 2;
    private int BAC = 4;
    
    /*
     * Modify board and hasQ arrary, return null if cannot place queen
     */
    private char[][] placeQueen(char[][] b, boolean[][] h, int i, int j) {
        System.out.println("PlaceQueen on i=" + i + " j=" + j);
	// Forward Diagonal
        //     0 1 2 3   h[2][0] - h[2][n-1]
        //   . . . . 4   h[3][0]
        //   . . . . 5   |
        //   . . . . 6   |
        //   . . . . 7*  h[3][n-1]
        // idx = i + j
        // h[idx/n + FOR][idx%4]
        
        // Backward Diagonal
        // 3 4 5 6 7*  h[4][3] h[5][0] - h[5][3]
        // 2 . . . .   |
        // 1 . . . .   |
        // 0 . . . .   h[4][0]
        //   . . . .
        // idx = n - 1 - i + j
        // h[idx/n + BAC][idx%4]
        
        // Check column
        if (h[COL][j] == true) return null;
        
        // Check row
        if (h[ROW][i] == true) return null;
            
        // Check forward diagonal
        int idx = i + j;
        if (h[idx / b.length + FOR][idx % b.length] == true) return null;
        
        // Check backward diagonal
        int idxBac = b.length - 1 - i + j;
	if (h[idxBac / b.length + BAC][idxBac % b.length] == true) return null;
        
        // Set new board with new Queen
        b[i][j] = 'Q';
        h[COL][j] = true;
        h[ROW][i] = true;
        h[idx / b.length + FOR][idx % b.length] = true;
        System.out.println("Backward " + (idxBac / b.length + BAC) + " " + idxBac % b.length);
	h[idxBac / b.length + BAC][idxBac % b.length] = true;
        System.out.println(i + "" + j);
        return b;
    }

}
