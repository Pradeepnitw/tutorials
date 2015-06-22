package leetcode.l1506;

public class PalindromePartitioningII {
    public int minCut(String s) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = s.length()-1; i >= 0; i--) {
    		sb.append(s.charAt(i));
    	}
        return Math.min(minCut(s, 0), minCut(sb.toString(), 0));
    }
    
    private int minCut(String s, int lo) {
        if (lo >= s.length()) return 0;
        int i = lo;
        int j = s.length();
        int last = s.length();
        
        while (i < j) {
            j = last - 1;
            while (s.charAt(j) != s.charAt(i)) {
                j--;
            }
            last = j;
            System.out.println("l=" + last + " i=" + i + " j=" + j);
            while (s.charAt(i) == s.charAt(j) && i < j) {
                i++;
                j--;
            }
            
            if (i < j) {
            	i = lo;
            	System.out.println("*l=" + last + " i=" + i + " j=" + j);
            }
        }
        System.out.println("-l=" + last + " i=" + i + " j=" + j);
        if (last == s.length()-1) return 0;
        
        return 1 + minCut(s, last+1);
    }
    
    public void runCode() {
//    	System.out.println(minCut("a"));
//    	System.out.println(minCut("cabababcbc"));
    	System.out.println(minCut("aaabaa"));
    }
    
    public static void main(String[] arg) {
    	new PalindromePartitioningII().runCode();
    }
}
