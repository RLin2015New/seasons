package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.service.base.BaseServerService;
import com.test.service.base.ServiceFactoryImpl;
import com.test.tools.SeasonLogTools;
import com.tools.StringTools;

/**
 * @projectName seasons
 * @fileName SeasonsStart.java
 * @description
 * @author lifl
 * @time 2018上午10:55:32
 *
 */

public class SeasonsStartServer {

	public static String configRoot = "config" + File.separator;
	public static String csvRoot = "resource" + File.separator + "csv"
			+ File.separator;
	public static String ibatisRoot = "configuration" + File.separator
			+ "ibatis" + File.separator;
	public static String mabatisRoot = "configuration" + File.separator
			+ "mybatis" + File.separator;
	private static SeasonConfig config;
	private static SeasonService service;
	private static SeasonSchema schema;

	public static void printWelcome() {
		SeasonLogTools.getLog().info("");
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", false));
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", true));
		SeasonLogTools.getLog().info("");
		String welcome = "Welcome to seasons!!!";
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("",
						(int) Math.floor((80 - welcome.length()) / 2), "  ",
						true) + welcome);
		SeasonLogTools.getLog().info("");
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", true));
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", true));
		SeasonLogTools.getLog().info("");
	}

	public static void main(String[] args) {
		printWelcome();
		SeasonLogTools.getLog().info("开始启动服务");
		try {
			config = new SeasonConfig();
			SeasonLogTools.getLog().info("配置加载完毕");
			service = new SeasonService();
			SeasonLogTools.getLog().info("服务加载完毕");
			schema = new SeasonSchema();
			SeasonLogTools.getLog().info("定时调度已启动");
		} catch (Exception e) {
			e.printStackTrace();
			SeasonLogTools.getLog().error("服务启动出错", e);
		}
		SeasonLogTools.getLog().info("服务启动完毕");
	}

	public static SeasonConfig getConfig() {
		return config;
	}

	public static SeasonService getService() {
		return service;
	}

	public static SeasonSchema getSchema() {
		return schema;
	}

	public static class SeasonConfig {
		private int serverId;
		private String serverType;
		private int serverPort;
		private int websocketPort;

		private SeasonConfig() throws IOException {
			Properties serverProperties = getProperties(SeasonsStartServer.configRoot
					+ "server.properties");
			serverId = Integer.parseInt(serverProperties
					.getProperty("server.id"));
			serverType = serverProperties.getProperty("server.type");
			serverPort = Integer.parseInt(serverProperties
					.getProperty("server.port"));
			websocketPort = Integer.parseInt(serverProperties
					.getProperty("server.websocketPort"));
		}

		public static Properties getProperties(String path) throws IOException {
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);
			fis.close();
			return properties;
		}

		/**
		 * @return the serverId
		 */
		public int getServerId() {
			return serverId;
		}

		/**
		 * @return the serverType
		 */
		public String getServerType() {
			return serverType;// 这里把引用暴露了
		}

		/**
		 * @return the serverPort
		 */
		public int getServerPort() {
			return serverPort;
		}

		/**
		 * @return the websocketPort
		 */
		public int getWebsocketPort() {
			return websocketPort;
		}

	}

	public static class SeasonService {
		private SeasonService() throws Exception {
			// 工厂生成
			httpService = ServiceFactoryImpl.getInstance()
					.createHttpRequestService();
			websocketService = ServiceFactoryImpl.getInstance()
					.createWebsocketService();
			dbService = ServiceFactoryImpl.getInstance().createDBService();
			configLoadService = ServiceFactoryImpl.getInstance()
					.createConfigLoadService();
			// propertiesLoadService = ServiceFactoryImpl.getInstance()
			// .createPropertiesLoadService();
			// 初始化
			httpService.init();
			websocketService.init();
			dbService.init();
			configLoadService.init();
			// propertiesLoadService.init();
		}

		private BaseServerService httpService;
		private BaseServerService websocketService;
		private BaseServerService dbService;
		private BaseServerService configLoadService;
		// private BaseServerService propertiesLoadService;
	}

	public static class SeasonSchema {

	}

}
