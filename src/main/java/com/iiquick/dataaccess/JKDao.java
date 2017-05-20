package com.iiquick.dataaccess;

import java.util.List;

import com.iiquick.domain.Result;

public interface JKDao {

	public List<Result> getJKByEnword(String enword);

	public List<Result> getJKByJmword(String jmword);

	public List<Result> getJKByMmword(String mmword);

}
