package leetcode.l1506;


public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        StringBuilder res = new StringBuilder();
        
        // match each strs[j].charAt(i)
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            
            int j = 0;
            while (j < strs.length) {
                // if i is not exceeding the size of string j
                if (strs[j].length() != i && strs[j].charAt(i) == c) {
                    j++;
                } else {
                    return res.toString();
                }
            }
            res.append(c);
        }
        
        return res.toString();
    }
}
