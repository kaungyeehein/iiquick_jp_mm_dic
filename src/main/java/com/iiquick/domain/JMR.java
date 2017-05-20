package com.iiquick.domain;

import java.io.Serializable;

public class JMR implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887659756510896888L;
	private String jmword;
	private String jmromaji;
	public String getJmword() {
		return jmword;
	}
	public void setJmword(String jmword) {
		this.jmword = jmword;
	}
	public String getJmromaji() {
		return jmromaji;
	}
	public void setJmromaji(String jmromaji) {
		this.jmromaji = jmromaji;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
