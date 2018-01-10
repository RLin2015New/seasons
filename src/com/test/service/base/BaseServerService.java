package com.test.service.base;

/**
 * @projectName seasons
 * @fileName BaseServerService.java
 * @description
 * @author lifl
 * @time 2018下午2:41:44
 *
 */

public interface BaseServerService {
	public static enum ServerState{
		notStart,//
		starting,//
		started,//
		stoped
		
	};
	public ServerState getState();

	public void init() throws Exception;

	public void stop();
}
