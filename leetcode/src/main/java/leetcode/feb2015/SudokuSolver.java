package leetcode.feb2015;

public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		board = recur(board);

//		System.out.println("No Solution Found:" + board == null);
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++)
//				System.out.print(board[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println();
	}

	private char[][] recur(char[][] board) {
		// Find the first empty cell to work on
		// If there's more than 0 possible way to fill this cell
		// Recurse all possible ways
		// If there no possible way to fill this cell, return null

		// Find an empty cell, if no, return the board
		int iIdx = -1;
		int jIdx = -1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					iIdx = i;
					jIdx = j;
					break;
				}
			}
			if (iIdx != -1) break;
		}
		// No more empty cell, return the board as solution
		if (iIdx == -1) return board;

		// Compute how many possible number can fit in this cell
		boolean[] impossibleNumbers = new boolean[10];
		// Check row i
		for (int j = 0; j < 9; j++) {
			if (board[iIdx][j] != '.') {
				impossibleNumbers[board[iIdx][j] - '0'] = true;
			}
		}

		// Check column j
		for (int i = 0; i < 9; i++) {
			if (board[i][jIdx] != '.') {
				impossibleNumbers[board[i][jIdx] - '0'] = true;
			}
		}

		// Check current square
		for (int i = 3*(iIdx/3); i < 3*(iIdx/3) + 3; i++) {
			for (int j = 3*(jIdx/3); j < 3*(jIdx/3) + 3; j++) {
				if (board[i][j] != '.')
					impossibleNumbers[board[i][j] - '0'] = true;
			}
		}

		char[][] res = null;
		for (int k = 1; k < 10; k++) {
			if (impossibleNumbers[k] == false) {
				// Fill this number
				char[][] newBoard = new char[board.length][board[0].length];

				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++)
						newBoard[i][j] = board[i][j];
				}

				newBoard[iIdx][jIdx] = (char)(k + '0');
				res = recur(newBoard);
			}
			if (res != null) return res;
		}

		return res;
	}

	public static void main(String[] arg) {
		char[][] b = new char[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				b[i][j] = '.';
		//     0 1 2   3 4 5   6 7 8
		// 0   5 3 .   . 7 .   . . .
		// 1   6 . .   1 9 5   . . .
		// 2   . 9 8   . . .   . 6 .
		//
		// 3   8 . .   . 6 .   . . 3
		// 4   4 . .   8 . 3   . . 1
		// 5   7 . .   . 2 .   . . 6
		//
		// 6   . 6 .   . . .   2 8 .
		// 7   . . .   4 1 9   . . 5
		// 8   . . .   . 8 .   . 7 9
		b[0][0] = '5'; b[0][1] = '3'; b[0][4] = '7';
		b[1][0] = '6'; b[1][3] = '1'; b[1][4] = '9'; b[1][5] = '5';
		b[2][1] = '9'; b[2][2] = '8'; b[2][7] = '6';
		b[3][0] = '8'; b[3][4] = '6'; b[3][8] = '3';
		b[4][0] = '4'; b[4][3] = '8'; b[4][5] = '3'; b[4][8] = '1';
		b[5][0] = '7'; b[5][4] = '2'; b[5][8] = '6'; 
		b[6][1] = '6'; b[6][6] = '2'; b[6][7] = '8'; 
		b[7][3] = '4'; b[7][4] = '1'; b[7][5] = '9'; b[7][8] = '5'; 
		b[8][4] = '8'; b[8][7] = '7'; b[8][8] = '9'; 

		
		b = new char[][]{"..9748...".toCharArray(),
				"7........".toCharArray(),
				".2.1.9...".toCharArray(),
				"..7...24.".toCharArray(),
				".64.1.59.".toCharArray(),
				".98...3..".toCharArray(),
				"...8.3.2.".toCharArray(),
				"........6".toCharArray(),
				"...2759..".toCharArray()};
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}
		System.out.println();

		new SudokuSolver().solveSudoku(b);
	}
}
