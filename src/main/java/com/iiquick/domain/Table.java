package com.iiquick.domain;

import java.io.Serializable;

public class Table implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8507268735848341490L;
	private int id;
	private short wtid;
	private int word1id;
	private int word2id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public short getWtid() {
		return wtid;
	}
	public void setWtid(short wtid) {
		this.wtid = wtid;
	}
	public int getWord1id() {
		return word1id;
	}
	public void setWord1id(int word1id) {
		this.word1id = word1id;
	}
	public int getWord2id() {
		return word2id;
	}
	public void setWord2id(int word2id) {
		this.word2id = word2id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
