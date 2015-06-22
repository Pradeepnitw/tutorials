package leetcode.feb2015;

import java.util.*;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        HashMap<Integer, Integer> allNum = new HashMap<Integer, Integer>();
        
        for (int i: nums) {
            if (allNum.containsKey(i)) {
                allNum.put(i, allNum.get(i) + 1);
            } else {
                allNum.put(i, 1);
            }
        }
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        recurse(res, allNum, nums.length, new ArrayList<Integer>());
        return res;
    }
    
    private void recurse(ArrayList<List<Integer>> res, HashMap<Integer, Integer> allNums, int numsLeft, ArrayList<Integer> list) {
        if (numsLeft == 0) {
            res.add(list);
            return;
        }
        
        for (int i: allNums.keySet()) {
            HashMap<Integer, Integer> newMap = (HashMap<Integer, Integer>) allNums.clone();
            if (allNums.get(i) == 1) {
                newMap.remove(i);
            } else {
                newMap.put(i, allNums.get(i)-1);
            }
            
            ArrayList<Integer> newList = (ArrayList<Integer>) list.clone();
            newList.add(i);
            recurse(res, newMap, numsLeft-1, newList);
        }
        return;
    }
    
    public static void main(String[] arg) {
    	int[] nums = new int[]{1,1,2};
    	for (List<Integer> l: new PermutationsII().permuteUnique(nums)) {
    		System.out.println(l.toString());
    	}
    }
}
