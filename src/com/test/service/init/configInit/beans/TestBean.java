package com.test.service.init.configInit.beans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.base.BaseConfigBean;
import com.tools.CSVTools;

/**
 * @projectName seasons
 * @fileName TestBean.java
 * @description
 * @author lifl
 * @time 2018上午11:06:51
 *
 */

public class TestBean extends BaseConfigBean {

	private int id;
	private String name;
	private Boolean open;
	private long value;
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public List<? extends BaseConfigBean> getBeansByFile(String filePath,
			boolean includeHeader) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, ParseException {
		List<TestBean> tbs = new ArrayList<TestBean>();
		List<String[]> files = CSVTools.readCSV(filePath, includeHeader);
		String[] header = files.get(0);
		for (int i = 1; i < files.size(); i++) {
			TestBean bean = (TestBean) getBeanByLine(TestBean.class, header,
					files.get(i));
			tbs.add(bean);
		}
		return tbs;
	}
}
