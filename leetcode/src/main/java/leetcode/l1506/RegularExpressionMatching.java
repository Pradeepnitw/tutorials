package leetcode.l1506;

public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		boolean[][] isMatch = new boolean[s.length()][p.length()];
		return isMatch(s, 0, p, 0, isMatch);
	}

	private boolean isMatch(String s, int i, String p, int j, boolean[][] isMatch) {
//		System.out.println(i + " " + j);
		if (i >= s.length() && j >= p.length()) return true;
		if (j >= p.length()) return false;
		if (i >= s.length()) {
			if (j != p.length()-1 && p.charAt(j+1) == '*')
				return isMatch(s, i, p, j+2, isMatch);
			return false;
		}
		
		if (isMatch[i][j]) return true;

		// character match
		if (p.charAt(j) == s.charAt(i)) {
			if (j == p.length() - 1) {
				if (i == s.length() - 1) {
					isMatch[i][j] = true;
					return true;
				} else return false;
			} else if (p.charAt(j+1) != '*') {
				if (isMatch(s, i+1, p, j+1, isMatch)) {
					isMatch[i][j] = true;
					return true;
				} else return false;
			}
		}

		// '*' Matches zero or more of the preceding element
		if (j != p.length() - 1 && p.charAt(j+1) == '*') {
			if (isMatch(s, i, p, j+2, isMatch)) {
				isMatch[i][j] = true;
				return true;
			}
			
			while (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
//				System.out.println("Iter" + i + " " + j);
				if (isMatch(s, i+1, p, j+2, isMatch)) {
					isMatch[i][j] = true;
					return true;
				}
				i++;
			}
		}
		
		// '.' Matches any single character
		if (p.charAt(j) == '.') {
			if (isMatch(s, i+1, p, j+1, isMatch)) {
				isMatch[i][j] = true;
				return true;
			} else return false;
				
		}
		
		return false;
	}

	public static void main(String[] arg) {
		System.out.println(new RegularExpressionMatching().isMatch("ab", ".*c"));
	}
}
