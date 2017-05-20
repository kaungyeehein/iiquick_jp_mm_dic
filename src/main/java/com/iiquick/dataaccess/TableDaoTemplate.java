package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.Table;

public class TableDaoTemplate extends SqlMapClientTemplate implements TableDao {

	@Override
	synchronized public void insertT1(Table table) {
		insert("Table.insertT1", table);
	}
	@Override
	synchronized public void insertT2(Table table) {
		insert("Table.insertT2", table);
	}
	@Override
	synchronized public void insertT3(Table table) {
		insert("Table.insertT3", table);
	}
	@Override
	synchronized public void insertT4(Table table) {
		insert("Table.insertT4", table);
	}
	@Override
	synchronized public void insertT5(Table table) {
		insert("Table.insertT5", table);
	}
	@Override
	synchronized public void insertT6(Table table) {
		insert("Table.insertT6", table);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT1() {
		return (List<Table>) queryForList("Table.getAllT1", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT2() {
		return (List<Table>) queryForList("Table.getAllT2", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT3() {
		return (List<Table>) queryForList("Table.getAllT3", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT4() {
		return (List<Table>) queryForList("Table.getAllT4", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT5() {
		return (List<Table>) queryForList("Table.getAllT5", null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Table> getAllT6() {
		return (List<Table>) queryForList("Table.getAllT6", null);
	}

	@Override
	public boolean checkT1(Table table) {
		table = ((Table)queryForObject("Table.checkT1", table));
		if (table != null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkT2(Table table) {
		table = ((Table)queryForObject("Table.checkT2", table));
		if (table != null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkT3(Table table) {
		table = ((Table)queryForObject("Table.checkT3", table));
		if (table != null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkT4(Table table) {
		table = ((Table)queryForObject("Table.checkT4", table));
		if (table != null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkT5(Table table) {
		table = ((Table)queryForObject("Table.checkT5", table));
		if (table != null)
			return false;
		else
			return true;
	}
	@Override
	public boolean checkT6(Table table) {
		table = ((Table)queryForObject("Table.checkT6", table));
		if (table != null)
			return false;
		else
			return true;
	}
	
	@Override
	synchronized public void increaseCorrect(String table, int id) {
		if(table.equals("t1")){
			update("Table.increaseCorrectT1", id);
		}else if(table.equals("t2")){
			update("Table.increaseCorrectT2", id);
		}else if(table.equals("t3")){
			update("Table.increaseCorrectT3", id);
		}else if(table.equals("t4")){
			update("Table.increaseCorrectT4", id);
		}else if(table.equals("t5")){
			update("Table.increaseCorrectT5", id);
		}else if(table.equals("t6")){
			update("Table.increaseCorrectT6", id);
		}
	}
	
	@Override
	synchronized public void increaseIncorrect(String table, int id) {
		if(table.equals("t1")){
			update("Table.increaseIncorrectT1", id);
		}else if(table.equals("t2")){
			update("Table.increaseIncorrectT2", id);
		}else if(table.equals("t3")){
			update("Table.increaseIncorrectT3", id);
		}else if(table.equals("t4")){
			update("Table.increaseIncorrectT4", id);
		}else if(table.equals("t5")){
			update("Table.increaseIncorrectT5", id);
		}else if(table.equals("t6")){
			update("Table.increaseIncorrectT6", id);
		}
	}
	
	@Override
	synchronized public void deleteTable(String table, int id) {
		if(table.equals("t1")){
			update("Table.deleteT1", id);
		}else if(table.equals("t2")){
			update("Table.deleteT2", id);
		}else if(table.equals("t3")){
			update("Table.deleteT3", id);
		}else if(table.equals("t4")){
			update("Table.deleteT4", id);
		}else if(table.equals("t5")){
			update("Table.deleteT5", id);
		}else if(table.equals("t6")){
			update("Table.deleteT6", id);
		}
	}

	@Override
	public int count() {
		Integer countT1 = ((Integer)queryForObject("Table.countT1", null));
		Integer countT2 = ((Integer)queryForObject("Table.countT2", null));
		Integer countT3 = ((Integer)queryForObject("Table.countT3", null));
		Integer countT4 = ((Integer)queryForObject("Table.countT4", null));
		Integer countT5 = ((Integer)queryForObject("Table.countT5", null));
		Integer countT6 = ((Integer)queryForObject("Table.countT6", null));
		return (countT1+countT2+countT3+countT4+countT5+countT6);
	}
	
}
