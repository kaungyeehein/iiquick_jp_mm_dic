package com.iiquick.dataaccess;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.iiquick.domain.WType;

public class WTypeDaoTemplate extends SqlMapClientTemplate implements WTypeDao {

	@Override
	public void insertWType(String name, String description) {
		WType wtype = new WType();
		wtype.setValue(name);
		wtype.setDesc(description);
		insert("WType.insert", wtype);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WType> getAllWType() {
		return ((List<WType>)queryForList("WType.getAll", null));
	}

}
