package leetcode.l1506;

import java.util.*;

public class TheSkylineProblem {
	public List<int[]> getSkylineIterative(int[][] b) {
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
					//	                	System.out.println("x=" + x + " listOfBuilding " + b[i][0] + "," + b[i][1] + "," + b[i][2]);

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
					//	                	System.out.println("x=" + x + " TopBuilding " + b[i][0] + "," + b[i][1] + "," + b[i][2]);
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







	/* 
	 * Merge sort solution
	 */

	public List<int[]> getSkyline(int[][] b) {
		List<List<int[]>> skylines = getEachSkyline(b);
		return mergeSkyline(skylines);
	}

	private List<int[]> mergeSkyline(List<List<int[]>> skylines) {
		
		while (skylines.size() > 1) {
			List<List<int[]>> merged = new ArrayList<List<int[]>>();
			int i = 0;
			
			while (i+1 < skylines.size()) {
				merged.add(mergeSkyline(skylines.get(i), skylines.get(i+1)));
//				System.out.println("!!!! i=" + i);
				i += 2;
			}
			if (i < skylines.size()) {
				merged.add(skylines.get(i));
			}
			skylines = merged;
		}
		return skylines.get(0);
	}

	private List<List<int[]>> getEachSkyline(int[][] b) {
		List<List<int[]>> res = new ArrayList<List<int[]>>();
		
		for (int i = 0; i < b.length; i++) {
			ArrayList<int[]> line = new ArrayList<int[]>();
			line.add(new int[]{b[i][0], b[i][2]});
			line.add(new int[]{b[i][1], 0});
			res.add(line);
		}
		
		return res;
	}

	private List<int[]> mergeSkyline(List<int[]> sl1, List<int[]> sl2) {
		System.out.print("Merging 2 skylines: ");
		for (int[] a: sl1) {
			System.out.print("[" + a[0] + " " + a[1] + "]");
		}
		System.out.print(" with ");
		for (int[] a: sl2) {
			System.out.print("[" + a[0] + " " + a[1] + "]");
		}
		System.out.println();
		
		List<int[]> res = new ArrayList<int[]>();

		int i1 = 0, i2 = 0;
		int h1 = 0, h2 = 0;

		while (i1 < sl1.size() && i2 < sl2.size()) {
			int x1 = sl1.get(i1)[0];
			int y1 = sl1.get(i1)[1];
			int x2 = sl2.get(i2)[0];
			int y2 = sl2.get(i2)[1];
			
			System.out.println("-[" + x1+ " "+y1+"] i1:"+i1 
					+" h1:" + h1 + " ["+x2+" "+y2+"] i2:" + i2 + " h2:" + h2);
			
			if (x1 == x2) {
				if (y2 != h1 || y1 != h2) {
					res.add(new int[]{x1, Math.max(y1, y2)});
				}
				
				h1 = y1;
				i1++;
				h2 = y2;
				i2++;
			}
			
			if (x1 < x2) { 
				if (y1 > h2 || h2 == 0) {
					res.add(sl1.get(i1));
				} else if (y1 < h2 && h1 > h2) {
					res.add(new int[]{x1, h2});
				}
				h1 = y1;
				i1++;
			} else if (x1 > x2){
				if (y2 > h1 || h1 == 0) {
					res.add(sl2.get(i2));
				} else if (y2 < h1 && h2 > h1) {
					res.add(new int[]{x2, h1});
				}
				h2 = y2;
				i2++;
			}
		}
		
		while (i1 < sl1.size()) {
			res.add(sl1.get(i1));
			i1++;
		}
		
		while (i2 < sl2.size()) {
			res.add(sl2.get(i2));
			i2++;
		}

		for (int[] a: res) {
			System.out.print("[" + a[0] + " " + a[1] + "]");
		}
		System.out.println();
		
		return res;
	}

	public void runCode() {
//		int[][] b = new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		
		int[][] b = new int[][]{{0,5,7},{5,10,7},{5,10,12},{10,15,7},{15,20,7},{15,20,12},{20,25,7}};
		
//		for (List<int[]> l:getEachSkyline(b)) {
//			for (int[] a: l) {
//				System.out.print("[" + a[0] + " " + a[1] + "]");
//			}
//			System.out.println();
//		}
		
		for (int[] a: getSkyline(b)) {
			System.out.print("[" + a[0] + " " + a[1] + "]");
		}
		System.out.println();
	}

	public static void main(String[] arg) {
		new TheSkylineProblem().runCode();
	}
}
