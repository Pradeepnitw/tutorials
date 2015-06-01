package leetcode.feb2015;

public class StringToInteger {

	public int myAtoi(String str) {
		if (str == null || str.length() == 0) return 0;

		str = str.trim();

		int i = 0;
		int val = 0;
		boolean isPos = true;

		if (str.charAt(0) == '-') {
			isPos = false;
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}

		while (i < str.length() && str.charAt(i) <= '9' && str.charAt(i) >= '0') {
			int curr = val;
			val = val*10 + str.charAt(i++) - '0';
			if (val == Integer.MIN_VALUE && !isPos) return Integer.MIN_VALUE;
			if (val/10 != curr) return isPos?Integer.MAX_VALUE:Integer.MIN_VALUE;
		}

		return isPos?val:(-1)*val;

	}

}
