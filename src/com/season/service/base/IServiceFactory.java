package com.season.service.base;

/**
 * @projectName seasons
 * @fileName ServiceFactory.java
 * @description
 * @author lifl
 * @time 2018下午3:16:29
 *
 */

public abstract class IServiceFactory {
	public abstract IServerService createHttpRequestService();

	public abstract IServerService createWebsocketService();

	public abstract IServerService createDBService();

	public abstract IServerService createConfigLoadService();

	public abstract IServerService createPropertiesLoadService();
}
