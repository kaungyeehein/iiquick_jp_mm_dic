package com.iiquick.dataaccess;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class RhDataSourceFactory {

	public DataSource createDataSource() {
		BasicDataSource ds = new BasicDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	    String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	    String name = "iiquick";
	    String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
	    ds.setUrl(url);
	    ds.setUsername("adminxQrhC5v");
	    ds.setPassword("rATUE3Wc2Ups");
	    return ds;
    }

}
