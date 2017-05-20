package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Result;

public interface MMDao {

	public List<Result> getMMByEnword(String enword);

	public List<Result> getMMByJkword(String jkword);

	public List<Result> getMMByJmword(String jmword);

}
