package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.Result;

public class JKDaoTemplate extends SqlMapClientTemplate implements JKDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJKByEnword(String enword) {
		return ((List<Result>)queryForList("JK.getJKByEnword", enword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJKByJmword(String jmword) {
		return ((List<Result>)queryForList("JK.getJKByJmword", jmword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJKByMmword(String mmword) {
		return ((List<Result>)queryForList("JK.getJKByMmword", mmword));
	}
	
}
