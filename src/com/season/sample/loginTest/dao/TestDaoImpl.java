package com.season.sample.loginTest.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.season.base.BaseDao;
import com.season.sample.loginTest.bean.TestBean;
import com.season.service.db.mysql.mybatis.mapper.TestBeanMapper;

/**
 * @projectName seasons
 * @fileName TestDaoImpl.java
 * @description
 * @author lifl
 * @time 2018下午4:58:45
 *
 */

public class TestDaoImpl extends BaseDao {
	private static TestDaoImpl ins = new TestDaoImpl();// 饿汉

	private TestDaoImpl() {

	}

	public static TestDaoImpl getInstance() {
		return ins;
	}

	public TestBean ibatisTestSelect(int id) throws SQLException {

		/**
		 * ibatis测试实现
		 */
		return (TestBean) sqlClient.queryForObject("nyUser.getAnotherBean", id);
	}

	public TestBean mybatisTestSelect01(int id) {
		/**
		 * mybatis测试实现 01
		 */
		SqlSession session = sqlSessionFactory.openSession();
		try {
			TestBean blog = (TestBean) session
					.selectOne(
							"com.test.service.db.mysql.mybatis.mapper.TestBeanMapper.getAnotherBean",
							1);
			return blog;
		} finally {
			session.close();
		}
	}

	public TestBean mybatisTestSelect02(int id) {
		/**
		 * 第二种访问方式
		 */
		SqlSession session = sqlSessionFactory.openSession();
		try {
			TestBeanMapper mapper = session.getMapper(TestBeanMapper.class);
			TestBean blog = mapper.selectTestBean(1);
			return blog;
		} finally {
			session.close();
		}
	}
}
