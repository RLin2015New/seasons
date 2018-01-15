package com.test.base;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @projectName seasons
 * @fileName BaseDao.java
 * @description
 * @author lifl
 * @time 2018下午3:58:12
 *
 */

public class BaseDao {
	protected static SqlMapClient sqlClient;
	protected static SqlSessionFactory sqlSessionFactory;

	public static void initSqlMap(SqlMapClient sql) {
		sqlClient = sql;
	}

	public static void initSqlFactory(SqlSessionFactory factory) {
		sqlSessionFactory = factory;
	}

}
