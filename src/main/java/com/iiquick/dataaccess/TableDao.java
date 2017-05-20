package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Table;

public interface TableDao {

	public void insertT1(Table table);
	public void insertT2(Table table);
	public void insertT3(Table table);
	public void insertT4(Table table);
	public void insertT5(Table table);
	public void insertT6(Table table);
	
	public List<Table> getAllT1();
	public List<Table> getAllT2();
	public List<Table> getAllT3();
	public List<Table> getAllT4();
	public List<Table> getAllT5();
	public List<Table> getAllT6();
	
	public boolean checkT1(Table table);	
	public boolean checkT2(Table table);	
	public boolean checkT3(Table table);	
	public boolean checkT4(Table table);	
	public boolean checkT5(Table table);	
	public boolean checkT6(Table table);
	
	public void increaseCorrect(String table, int id);	
	public void increaseIncorrect(String table, int id);	
	public void deleteTable(String table, int id);
	
	public int count();	
	
}
