package leetcode.feb2015;

import java.util.*;

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] b) {
        List<int[]> res = new ArrayList<int[]>();
        if (b == null || b.length == 0 || b[0].length == 0) return res;
        // b[][0] Left x
        // b[][1] Right x
        // b[][2] Highet
        
        // Pre-process what's all the x-coord we want to visist
        // And for each of these coord, what's the buildings that's involoved
        
        // <x-coord, List<Building#>>
        TreeMap<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        
        for (int i = 0; i < b.length; i++) {
            if (!map.containsKey(b[i][0])) {
                map.put(b[i][0], new LinkedList<Integer>());
            }
            
            if (!map.containsKey(b[i][1])) {
                map.put(b[i][1], new LinkedList<Integer>());
            }
        }
        
        for (int i = 0; i < b.length; i++) {
        	for (int x : map.subMap(b[i][0], true, b[i][1], true).keySet()) {
            	map.get(x).add(i);
            }
                
            
        }

        
        for (int x : map.keySet()) {
            // If there's only 1 building in the list
            if (map.get(x).size() == 1) {
                int bld = map.get(x).get(0);
                // x is the left up of this building
                if (b[bld][0] == x) {
                    res.add(new int[]{x, b[bld][2]});
                }
                // x is the right up of this building
                else if (b[bld][1] == x) {
                    res.add(new int[]{x, 0});
                }
            } else {
                // Get idx of tallest buildings
                List<Integer> listOfBuilding = map.get(x);
                int topHeight = 0;
                ArrayList<Integer> buildingWithTopHeight = new ArrayList<Integer>();
                
                for (int i: listOfBuilding) {
//                	System.out.println("x=" + x + " listOfBuilding " + b[i][0] + "," + b[i][1] + "," + b[i][2]);
                    
                	if (b[i][2] > topHeight) {
                        topHeight = b[i][2];
                        buildingWithTopHeight = new ArrayList<Integer>();
                        buildingWithTopHeight.add(i);
                    } else if (b[i][2] == topHeight) {
                        buildingWithTopHeight.add(i);
                    }
                }
                
                boolean isAllLeft = true;
                boolean isAllRight = true;
                
                for (int i:buildingWithTopHeight) {
//                	System.out.println("x=" + x + " TopBuilding " + b[i][0] + "," + b[i][1] + "," + b[i][2]);
                    if (b[i][0] != x) {
                        isAllLeft = false; 
                    }
                    if (b[i][1] != x) {
                        isAllRight = false;
                    }
                }
                
                // If x is the left of all the tallest buildings, add point
                if (isAllLeft) {
                    res.add(new int[]{x, topHeight});
                }
                
                // If x is the right of the tallest building
                // add (x, highest of second tallest building, which is not have x as right coord)
                if (isAllRight) {
                    int secondTallest = -1;
                    for (int i:listOfBuilding) {
                        if (!buildingWithTopHeight.contains(i) && b[i][1] != x) {
                            if (secondTallest == -1)
                                secondTallest = i;
                            else if (b[i][2] > b[secondTallest][2]) {
                                secondTallest = i;
                            }
                        }
                    }
                    if (secondTallest != -1) {
                        res.add(new int[]{x, b[secondTallest][2]});
                    }
                }
                
                // If the highest buildings continues, we don't add them to the result
            }
        }
        return res;
    }
    
    public static void main(String[] arg) {
    	int[][] b = new int[][] {
    			new int[]{0,3,3},
    			new int[]{1,5,3},
    			new int[]{2,4,3},
    			new int[]{3,7,3}
    	};
    	
    	for (int[] a :new TheSkylineProblem().getSkyline(b)) {
    		System.out.println(a[0] + " " + a[1]);
    	}
    }
}
