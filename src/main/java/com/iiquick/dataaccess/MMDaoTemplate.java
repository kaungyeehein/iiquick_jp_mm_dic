package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.Result;

public class MMDaoTemplate extends SqlMapClientTemplate implements MMDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getMMByEnword(String enword) {
		return ((List<Result>)queryForList("MM.getMMByEnword", enword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getMMByJkword(String jkword) {
		return ((List<Result>)queryForList("MM.getMMByJkword", jkword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getMMByJmword(String jmword) {
		return ((List<Result>)queryForList("MM.getMMByJmword", jmword));
	}
	
}
