package app.common;

import java.util.Comparator;

public class WordTupleByScoreComparator implements Comparator<WordTuple> {

	@Override
	public int compare(WordTuple o1, WordTuple o2) {
		return o2.getScore() - o1.getScore();
	}
	
}
