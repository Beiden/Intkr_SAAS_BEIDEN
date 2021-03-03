package com.intkr.saas.engine.db;

import javax.sql.DataSource;

import com.intkr.saas.engine.spring.SpringContext;

/**
 * 
 * @author Beiden
 * @date 2017-10-26
 * @version 1.0
 */
public class DataSourceEngine {

	public static DataSource getDataSource(String dataSource) {
		return SpringContext.getBean(dataSource);
	}

}
