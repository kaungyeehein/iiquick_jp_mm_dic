package com.iiquick.domain;

import java.io.Serializable;

public class Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7935230348560416213L;
	private int id;
	private String word1;
	private String word2;
	private String word3;
	private String wtype;
	private int correct;
	private int incorrect;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord1() {
		return word1;
	}
	public void setWord1(String word1) {
		this.word1 = word1;
	}
	public String getWord2() {
		return word2;
	}
	public void setWord2(String word2) {
		this.word2 = word2;
	}
	public String getWord3() {
		return word3;
	}
	public void setWord3(String word3) {
		this.word3 = word3;
	}
	public String getWtype() {
		return wtype;
	}
	public void setWtype(String wtype) {
		this.wtype = wtype;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	public int getIncorrect() {
		return incorrect;
	}
	public void setIncorrect(int incorrect) {
		this.incorrect = incorrect;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
