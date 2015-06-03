package leetcode.l1506;

import java.util.*;

public class CountPrimes {
    public int countPrimes(int n) {
        boolean[] notPrimeNums = new boolean[n];
        
        int x = 2;
        while (x < Math.sqrt(n)) {
            int y = x;
            ArrayList<Integer> idxToSetTrue = new ArrayList<Integer>();
            
            while (x * y < n) {
                idxToSetTrue.add(x * y);
                y = nextFalse(notPrimeNums, y);
            }
            for (int i: idxToSetTrue) {
                notPrimeNums[i] = true;
            }
            
            x = nextFalse(notPrimeNums, x);
        }
        
        int count = 0;
        
        for (int i = 2; i < n; i++) {
            if (!notPrimeNums[i]) {
                count++;
                System.out.print(i + " ");
            }
        }
        
        return count;
    }
    
    private int nextFalse(boolean[] b, int idx) {
        idx++;
        while (idx < b.length && b[idx] == true)
            idx++;
        return idx;
    }

    public int countPrimes22(int n) {
        TreeSet<Integer> heap = new TreeSet<Integer>();
        
        // nlog(n)/2
        for (int i = 1; i < n; i+=2) {
            if (i % 3 != 0 && i % 5 != 0) 
                heap.add(i);
        }
        
        int x = 7;
        while (x < Math.sqrt(n)) {
            // log(n)
            int y = x;
            
            while (x * y < n) {
                // log(n)
                heap.remove(y);
                // log(n)
                y = heap.higher(y);
            }
            
            // log(n)
            x = heap.higher(x);
        }
        
        return heap.size();
    }

    public static void main(String[] arg) {
        System.out.println(new CountPrimes().countPrimes(10));
    }

}
