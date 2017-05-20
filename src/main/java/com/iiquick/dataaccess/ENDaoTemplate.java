package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.Result;

public class ENDaoTemplate extends SqlMapClientTemplate implements ENDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getENByJkword(String jkword) {
		return ((List<Result>)queryForList("EN.getENByJkword", jkword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getENByJmword(String jmword) {
		return ((List<Result>)queryForList("EN.getENByJmword", jmword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getENByMmword(String mmword) {
		return ((List<Result>)queryForList("EN.getENByMmword", mmword));
	}
	
}
