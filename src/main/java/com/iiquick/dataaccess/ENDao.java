package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Result;

public interface ENDao {

	public List<Result> getENByJkword(String jkword);

	public List<Result> getENByJmword(String jmword);

	public List<Result> getENByMmword(String mmword);

}
