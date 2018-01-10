package com.test.web.httpRequest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import com.jettyServer.JettyServer;
import com.jettyServer.ServerConfig;
import com.test.SeasonsStartServer;
import com.test.service.base.AbstractBaseServerService;
import com.test.tools.SeasonLogTools;
import com.test.tt.login.LoginServlet;

/**
 * @projectName seasons
 * @fileName JettyServer.java
 * @description
 * @author lifl
 * @time 2018下午2:38:57
 *
 */

public class JettyServerService extends AbstractBaseServerService {
	private static Map<String, Class<? extends Servlet>> pathMap = new HashMap<String, Class<? extends Servlet>>();

	static {
		pathMap.put("/req/login", LoginServlet.class);
	}

	@Override
	public void init() {
		SeasonLogTools.getLog().info("开始初始化JettyServerService服务");
		setState(ServerState.starting);
		Thread thread = new Thread(new Runnable() {
			public void run() {
				ServerConfig conf = new ServerConfig();
				int port = SeasonsStartServer.getConfig().getServerPort();
				conf.setPort(port);
				for (String path : pathMap.keySet()) {
					conf.setContentPath(path);
					conf.addServer(pathMap.get(path), path);
				}
				try {
					SeasonLogTools.getLog().info("thread1 启动对于:" + port + "监听");
					JettyServer.startJetty(conf);
				} catch (Exception e) {
					e.printStackTrace();
					SeasonLogTools.getLog().error(
							"  JettyServerService error ", e);
				}

			}
		});
		thread.start();

		setState(ServerState.started);
	}

	@Override
	public void stop() {
		setState(ServerState.stoped);
	}

}
