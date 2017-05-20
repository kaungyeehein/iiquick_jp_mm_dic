package com.iiquick.dataaccess;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class AppDaoTemplate extends SqlMapClientTemplate implements AppDao{

	@Override
	public Integer getSearchCount() {
		String searchCount = (String) queryForObject("App.getSearchCount", null);
		return Integer.parseInt(searchCount);
	}

	@Override
	public void increaseSearchCount() {
		Integer searchCount = getSearchCount();
		String update = String.valueOf(++searchCount);
		update("App.increaseSearchCount", update);
	}

}
