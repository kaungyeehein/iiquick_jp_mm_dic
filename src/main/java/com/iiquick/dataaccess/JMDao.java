package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Result;

public interface JMDao {

	public List<Result> getJMByEnword(String enword);

	public List<Result> getJMByJkword(String jkword);

	public List<Result> getJMByMmword(String mmword);

}
