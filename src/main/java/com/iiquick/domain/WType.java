package com.iiquick.domain;

import java.io.Serializable;

public class WType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5914765865313911421L;
	private short id;
	private String value;
	private String desc;
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
