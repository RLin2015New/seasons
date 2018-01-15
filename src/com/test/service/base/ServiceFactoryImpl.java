package com.test.service.base;

import com.test.service.db.mysql.mybatis.MybatisService;
import com.test.service.init.configInit.ConfigLoadService;
import com.test.service.init.configInit.PropertiesLoadService;
import com.test.web.httpRequest.JettyServerService;
import com.test.web.websocket.WebSocketService;

/**
 * @projectName seasons
 * @fileName ServiceFactoryImpl.java
 * @description
 * @author lifl
 * @time 2018下午3:19:30
 *
 */
public class ServiceFactoryImpl extends ServiceFactory {
	/** 饿汉 **/
	private static ServiceFactoryImpl ins = new ServiceFactoryImpl();

	private ServiceFactoryImpl() {
	}

	public static ServiceFactoryImpl getInstance() {
		return ins;
	}

	public BaseServerService createHttpRequestService() {
		return new JettyServerService();
	}

	public BaseServerService createWebsocketService() {
		return new WebSocketService();
	}

	/**
	 * 工厂模式的好处~，平滑的切换服务
	 */
	public BaseServerService createDBService() {
//		return new IbatisService();
		 return new MybatisService();
	}

	public BaseServerService createConfigLoadService() {
		return new ConfigLoadService();
	}

	@Override
	public BaseServerService createPropertiesLoadService() {
		return new PropertiesLoadService();
	}
}
