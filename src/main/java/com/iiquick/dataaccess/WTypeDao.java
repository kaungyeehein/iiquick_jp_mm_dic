package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.WType;

public interface WTypeDao {
	
	 public void insertWType(String wtname, String wtdescription);
	 
	 public List<WType> getAllWType();
	
}
