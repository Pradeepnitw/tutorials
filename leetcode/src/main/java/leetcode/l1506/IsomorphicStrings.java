package leetcode.l1506;

import java.util.HashMap;
import java.util.HashSet;


public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> mapStoT = new HashMap<Character, Character>();
        HashSet<Character> mappedCharInT = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (!mapStoT.containsKey(s.charAt(i)) && !mappedCharInT.contains(t.charAt(i))) {
                mapStoT.put(s.charAt(i), t.charAt(i));
                mappedCharInT.add(t.charAt(i));
            } else if (!mapStoT.containsKey(s.charAt(i)) && mappedCharInT.contains(t.charAt(i))) {
                return false;
            } else {
                if (mapStoT.get(s.charAt(i)) != t.charAt(i)) return false;
            }
        }
        return true;
    }

}
