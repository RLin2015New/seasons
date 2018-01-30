package com.season.sample.loginTest.bean;

import com.season.base.BaseBean;

/**
 * @projectName seasons
 * @fileName TestBean.java
 * @description
 * @author lifl
 * @time 2018下午3:11:45
 *
 */

public class TestBean extends BaseBean {
	private int userId;
	private String name;

	@Override
	public String toString() {
		return userId + "_" + getName();
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
