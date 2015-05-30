package leetcode.feb2015;

import java.util.TreeMap;

public class PowXN {
	public double myPow(double x, int n) {
		TreeMap<Integer, Double> cache = new TreeMap<Integer, Double>();
		boolean isNeg = false;
		if (n < 0) {
			isNeg = true;
			n = n * (-1);
		}
		
		double res = 1;
		int m = n;
		cache.put(1, x);
		while (m != 0) {
			System.out.println(m);
			int idx = cache.floorKey(m);
			res *= cache.get(idx);
			m -= idx;
			if (!cache.containsKey(n-m))
				cache.put(n-m, res);
		}
		//cache.ceilingKey(K key)

		if (isNeg) {
			res = 1/res;
		}
		return res;
	}

	public static void main(String[] arg) {
		//System.out.println(new PowXN().myPow(8.88023, 3));
		System.out.println(new PowXN().myPow(34.00515, -3));
	}
}
