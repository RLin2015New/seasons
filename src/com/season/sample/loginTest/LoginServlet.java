package com.season.sample.loginTest;

import javax.servlet.http.HttpServletRequest;

import com.season.tools.SeasonLogTools;
import com.season.web.httpRequest.base.AbstractHttpServlet;
import com.tools.ReturnType;

/**
 * @projectName seasons
 * @fileName LoginServlet.java
 * @description
 * @author lifl
 * @time 2018下午3:07:07
 *
 */

public class LoginServlet extends AbstractHttpServlet {

	private static final long serialVersionUID = -764379266927174141L;

	private enum Req {
		test;
	}

	@Override
	protected void register() throws NoSuchMethodException, SecurityException {
		SeasonLogTools.testMsg("register");
		registerMethod(Req.test.name(),
				getDefaultMethod(LoginServlet.class, "testMethod"));
	}

	/**
	 * 
	 * @description 测试方法
	 * @param request
	 * @return String
	 * @time 2018下午3:33:04
	 */
	public String testMethod(HttpServletRequest request) {
		SeasonLogTools.testMsg("run testMethod");
		return ReturnType.querySuccess("run success").toString();
	}

}
