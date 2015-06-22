package interview.airbnb;

import java.util.Arrays;

public class ShortenURL {
	public static void main(String args[]) {
		
		System.out.println(findSecret("abcdefghijklm"));
	}

	public static boolean isTheSecret(String input) {
		return input.equals("abCdeFGhiJklM");
	}

	public static String findSecret(String input) {
		for (int i = 0; i < Math.pow(2, input.length()); i++){
			String s = Integer.toBinaryString(i);
			boolean[] b = new boolean[input.length()];
			Arrays.fill(b, false);
			
			for (int j = 1; j <= s.length(); j++) {
				if (s.charAt(s.length() - j) == '1') {
					b[b.length - j] = true;
				}
			}
			
			char[] test = input.toLowerCase().toCharArray();
			for (int j = 0; j < b.length; j++) {
				if (b[j]) test[j] = (char) (test[j] - 'a' + 'A');
			}
			if (isTheSecret(String.valueOf(test))) return String.valueOf(test);
		}
		
		return null;
	}
}
