package com.test.service.base;

import com.test.service.db.mysql.DBManagerService;
import com.test.service.init.configInit.ConfigLoadService;
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

	public BaseServerService createDBService() {
		return new DBManagerService();
	}

	public BaseServerService createConfigLoadService() {
		return new ConfigLoadService();
	}
}
