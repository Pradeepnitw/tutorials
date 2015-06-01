package leetcode.may2014;

import java.util.*;

public class WordBreak {
    public static int UNVISITED = -1;
    public static int FALSE = 0;
    public static int TRUE = 1;
    
    public boolean wordBreak(String s, Set<String> dict) {
        // Store result that's already computed
        int[] cache = new int[s.length()];
        for (int i=0; i<cache.length; i++)
            cache[i] = UNVISITED;
        return wordBreak(s, dict, cache);
    }
    
    private boolean wordBreak(String s, Set<String> dict, int[] cache) {
        if (cache[s.length()-1] != UNVISITED) {
            return cache[s.length()-1]==TRUE?true:false;
        }
        if (dict.contains(s)) {
            cache[s.length()-1] = TRUE;
            return true;
        }
        if (s == "" || s == null) return false;
        
        for (int i=1; i<=s.length()-1; i++) {
            // [abcdefg] length=7 , i=[1,6]
            // [a]  [bcdefg] 
            // (0,1)(1,7)
            // [abcdef] substring(0,6) maximum condition
            if (dict.contains(s.substring(0,i))) {
               if (wordBreak(s.substring(i,s.length()), dict, cache) == true) {
                   cache[s.length()-1] = TRUE;
                   return true;
               }
            }
        }
        cache[s.length()-1] = FALSE;
        return false;
    }
}
