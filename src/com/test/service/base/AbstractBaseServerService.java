package com.test.service.base;

/**
 * @projectName seasons
 * @fileName BaseService.java
 * @description
 * @author lifl
 * @time 2018下午2:39:38
 *
 */

public abstract class AbstractBaseServerService implements BaseServerService {

	private ServerState state;

	public boolean notStart() {
		return ServerState.notStart.equals(state);
	}

	public ServerState getState() {
		return state;
	}

	// 这里的一个改进是把设置状态封装起来，这样更便捷，但是目前没有看到这么做的必要性
	protected void setState(ServerState state) {
		this.state = state;
	}

}
