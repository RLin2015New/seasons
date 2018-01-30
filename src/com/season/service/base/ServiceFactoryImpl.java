package com.season.service.base;

import com.season.service.db.mysql.mybatis.MybatisService;
import com.season.service.init.configInit.ConfigLoadService;
import com.season.service.init.configInit.PropertiesLoadService;
import com.season.service.memcached.MemcachedService;
import com.season.service.shutdown.ShutDownService;
import com.season.web.httpRequest.JettyServerService;
import com.season.web.websocket.base.WebSocketService;

/**
 * @projectName seasons
 * @fileName ServiceFactoryImpl.java
 * @description
 * @author lifl
 * @time 2018下午3:19:30
 *
 */
public class ServiceFactoryImpl extends IServiceFactory {
	/** 饿汉 **/
	private static ServiceFactoryImpl ins = new ServiceFactoryImpl();

	private ServiceFactoryImpl() {
	}

	public static ServiceFactoryImpl getInstance() {
		return ins;
	}

	public IServerService createHttpRequestService() {
		return new JettyServerService();
	}

	public IServerService createWebsocketService() {
		return new WebSocketService();
	}

	/**
	 * 工厂模式的好处~，平滑的切换服务
	 */
	public IServerService createDBService() {
		// return new IbatisService();
		return new MybatisService();
	}

	public IServerService createConfigLoadService() {
		return new ConfigLoadService();
	}

	@Override
	public IServerService createPropertiesLoadService() {
		return new PropertiesLoadService();
	}

	public IServerService createShutDownService() {
		return new ShutDownService();
	}

	public IServerService createMemcachedService() {
		return new MemcachedService();
	}
}
