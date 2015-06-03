package leetcode.l1405;

import java.util.*;

public class PascalsTriangleII {
    public ArrayList<Integer> getRow(int k) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i=0; i<k-1; i++) {
            a.add(0);
        }
        
        a.add(1); // k-1
        if (k != 0)
            a.add(1); // k
        
        while (a.get(0) != 1) {
            for (int i=0; i<k; i++) {
                if (a.get(i+1) != 0) {
                    a.set(i, a.get(i)+a.get(i+1));
                }
            }
        }
        return a;
    }
}
