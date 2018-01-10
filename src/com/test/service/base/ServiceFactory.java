package com.test.service.base;

/**
 * @projectName seasons
 * @fileName ServiceFactory.java
 * @description
 * @author lifl
 * @time 2018下午3:16:29
 *
 */

public abstract class ServiceFactory {
	public abstract BaseServerService createHttpRequestService();

	public abstract BaseServerService createWebsocketService();

	public abstract BaseServerService createDBService();

	public abstract BaseServerService createConfigLoadService();
}
