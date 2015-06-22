package leetcode.l1506;

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] a, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        // O(nlogn)
        Arrays.sort(a);
        
        // 1D
        // O(n)
        int i = 0;
        // Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> sum = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        while (i < a.length && a[i] <= target) {
            ArrayList<Integer> l = new ArrayList<Integer>();
            l.add(a[i]);
            
            if (a[i] == target) {
                res.add(l);
            } else {
                // map.put(a[i], l);
                sum.add(a[i]);
                list.add(l);
            }
            i++;
        }
        
      
        
        // nD
        // O(n*n)
        while (!sum.isEmpty()) {
            // Map<Integer, ArrayList<Integer>> next = new HashMap<Integer, ArrayList<Integer>>();
            ArrayList<Integer> nextSum = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> nextList = new ArrayList<ArrayList<Integer>>();

            i = 0;
            while (i < a.length && a[i] < target) {
                // for (int k: map.keySet()) {
                for (int k = 0; k < sum.size(); k++) {
                    // if (a[i] < map.get(k).get(map.get(k).size()-1)) continue;
//                	System.out.println(sum.size() + " " + list.size());
                    if (a[i] < list.get(k).get(list.get(k).size()-1)) continue;
                    
                    if (a[i] + sum.get(k) == target) {
                        // map.get(k).add(a[i]);
                        list.get(k).add(a[i]);
                        res.add(list.get(k));
                    } else if (a[i] + sum.get(k) < target) {
                        ArrayList<Integer> l = new ArrayList<Integer>();
                        l.addAll(list.get(k));
                        l.add(a[i]);
                        nextSum.add(a[i] + sum.get(k));
                        nextList.add(l);
                    }
                }
                
                i++;
            }
            
            sum = nextSum;
            list = nextList;
        }
        return res;
    }
    
    public void runCode() {
    	this.combinationSum(new int[]{7, 3, 2}, 18);
    }
    
    public static void main(String[] arg) {
    	new CombinationSum().runCode();
    }
}
