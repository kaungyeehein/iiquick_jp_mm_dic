package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.Result;

public class JMDaoTemplate extends SqlMapClientTemplate implements JMDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJMByEnword(String enword) {
		return ((List<Result>)queryForList("JM.getJMByEnword", enword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJMByJkword(String jkword) {
		return ((List<Result>)queryForList("JM.getJMByJkword", jkword));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Result> getJMByMmword(String mmword) {
		return ((List<Result>)queryForList("JM.getJMByMmword", mmword));
	}
	
}
