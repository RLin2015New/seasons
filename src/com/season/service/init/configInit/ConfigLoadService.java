package com.season.service.init.configInit;

import java.util.List;

import com.season.SeasonsStartServer;
import com.season.service.base.AbstractBaseServerService;
import com.season.service.init.configInit.csvBeans.TestBean;
import com.season.tools.SeasonLogTools;

/**
 * @projectName seasons
 * @fileName ConfigLoadService.java
 * @description
 * @author lifl
 * @time 2018上午11:06:25
 *
 */

public class ConfigLoadService extends AbstractBaseServerService {
	private List<TestBean> tests;

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws Exception {
		SeasonLogTools.getLog().info("开始初始化ConfigLoadService服务");
		setState(ServerState.starting);
		tests = (List<TestBean>) new TestBean().getBeansByFile(
				SeasonsStartServer.csvRoot + "test.csv", false);
		setState(ServerState.started);
	}

	public List<TestBean> getTests() {
		return tests;
	}

	@Override
	public void stop() {
		setState(ServerState.stoped);
	}

}
