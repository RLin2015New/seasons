package com.test.tools;

import org.apache.log4j.Logger;

/**
 * @projectName seasons
 * @fileName SeasonLogTools.java
 * @description
 * @author lifl
 * @time 2018下午2:24:58
 *
 */
/** 在业务量未达到一定量级前，一个log就足够了；再往上，就考虑log框架 或者自己做系统日志和业务日志分离 **/
public class SeasonLogTools {
	private static Logger log; // 系统日志
	static {
		log = Logger.getLogger("msg");
	}

	public static Logger getLog() {
		return log;
	}
}
