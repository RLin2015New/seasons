package com.test.web.websocket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import com.jettyServer.JettyServer;
import com.jettyServer.ServerConfig;
import com.test.SeasonsStartServer;
import com.test.service.base.AbstractBaseServerService;
import com.test.tools.SeasonLogTools;

/**
 * @projectName seasons
 * @fileName WebSocketService.java
 * @description
 * @author lifl
 * @time 2018下午2:41:19
 *
 */

public class WebSocketService extends AbstractBaseServerService {

	private static Map<String, Class<? extends Servlet>> pathMap = new HashMap<String, Class<? extends Servlet>>();

	static {
		pathMap.put("/req/ws", WebSocketFactoryConfiguration.class);
	}

	@Override
	public void init() {
		SeasonLogTools.getLog().info("开始初始化WebSocketService服务");
		setState(ServerState.starting);

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerConfig websocket = new ServerConfig();
				int port = SeasonsStartServer.getConfig().getWebsocketPort();
				websocket.setPort(port);
				for (String path : pathMap.keySet()) {
					websocket.setContentPath(path);
					websocket.addServer(pathMap.get(path),
							path);
				}
				try {
					SeasonLogTools.getLog().info("thread2 启动对于:"
							+ port + "监听");
					JettyServer.startJetty(websocket);
				} catch (Exception e) {
					e.printStackTrace();
					SeasonLogTools.getLog().error("  WebSocketService error ", e);
				}
			}
		});
		thread2.start();
		setState(ServerState.started);
	}

	@Override
	public void stop() {
		setState(ServerState.stoped);
	}

}
