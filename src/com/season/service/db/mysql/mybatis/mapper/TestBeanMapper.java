package com.season.service.db.mysql.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.season.sample.loginTest.bean.TestBean;

/**
 * @projectName seasons
 * @fileName TestBeanMapper.java
 * @description
 * @author lifl
 * @time 2018下午3:13:34
 *
 */

public interface TestBeanMapper {
	@Select("select * from user_inf where userId = #{id}")
	public TestBean selectTestBean(int id);

	public TestBean getAnotherBean();
}
