package app.common;

import java.io.Serializable;

public class WordTuple implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public WordTuple(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected int getScore() {
		return score;
	}
	protected void setScore(int score) {
		this.score = score;
	}
	public String toString() {
		return "[" + name + "-" + score + "]";
	}
}
