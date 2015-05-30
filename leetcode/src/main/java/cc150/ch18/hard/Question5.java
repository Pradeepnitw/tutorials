package cc150.ch18.hard;

import java.util.HashMap;

public class Question5 {
	public int minDis(String[] textFile, String wordA, String wordB) {
		int idxA = -1;
		int idxB = -1;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < textFile.length; i++) {
			if (textFile[i].equalsIgnoreCase(wordA)) {
				idxA = i;
			}
			if (textFile[i].equalsIgnoreCase(wordB)) {
				idxB = i;
			}
			if (idxB != -1 && idxA != -1) {
				min = Math.min(Math.abs(idxA-idxB), min);
			}
		}
		
		if (min == Integer.MAX_VALUE)
			return -1;
		else return min;
	}
	
	private HashMap<String, HashMap<String, Integer>> res;
	
	public void initMinDisForAll(String[] textFile) {
		res = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> idx = new HashMap<String, Integer>();
		
		for (int i = 0; i < textFile.length; i++) {
			if (!idx.containsKey(textFile[i])) {
				idx.put(textFile[i], i);
				HashMap<String, Integer> listOfCurr = new HashMap<String, Integer>();
				
				for (String s: res.keySet()) {
					int dis = i - idx.get(s);
					res.get(s).put(textFile[i], dis);
					listOfCurr.put(s, dis);
				}
				res.put(textFile[i], listOfCurr);
			} else {
				idx.put(textFile[i], i);
				for (String s: res.get(textFile[i]).keySet()) {
					int dis = i - idx.get(s);
					if (dis < res.get(textFile[i]).get(s)) {
						res.get(textFile[i]).put(s, dis);
						res.get(s).put(textFile[i], dis);
					}
				}
			}
		}
	}
	
	public int getMinDisForAll(String a, String b) {
		if (res.containsKey(a) && res.get(a).containsKey(b)) {
			return res.get(a).get(b);
		} else return -1;
	}
	
	public static void main(String[] args) {
		Question5 obj = new Question5();
		String[] textFile = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Monday", "Wednesday", "Friday"};
		for (String s: textFile) {
			System.out.print(s + " ");
		}
		
		System.out.println("\nMonday to Friday:" + obj.minDis(textFile, "Monday", "Friday"));
		System.out.println("Monday to Sunday:" + obj.minDis(textFile, "Monday", "Sunday"));
		
		obj.initMinDisForAll(textFile);
		System.out.println("Monday to Friday:" + obj.getMinDisForAll("Monday", "Friday"));
		System.out.println("Monday to Sunday:" + obj.getMinDisForAll("Monday", "Sunday"));
	}
}
