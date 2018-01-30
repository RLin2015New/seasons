package com.season;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.season.service.base.IServerService;
import com.season.service.base.ServiceFactoryImpl;
import com.season.tools.SeasonLogTools;
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

	public static boolean isTest = true;

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

	public static void printStartSuccess() {
		SeasonLogTools.getLog().info("");
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", false));
		SeasonLogTools.getLog().info(
				StringTools.fullFillToLength("", 40, "##", true));
		SeasonLogTools.getLog().info("");
		String welcome = "Welcome to seasons!!! Server Started!";
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
			SeasonLogTools.getLog().error("服务启动出错", e);
			System.exit(0);
			return;
		}
		SeasonLogTools.getLog().info("服务启动完毕");
		printStartSuccess();
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
		private final String serverName;
		private final int serverId;
		private final String serverType;
		private final int serverPort;
		private final int websocketPort;
		private final String charset;

		private SeasonConfig() throws IOException {
			Properties serverProperties = getProperties(SeasonsStartServer.configRoot
					+ "server.properties");
			serverName = serverProperties.getProperty("server.name");
			serverId = Integer.parseInt(serverProperties
					.getProperty("server.id"));
			serverType = serverProperties.getProperty("server.type");
			serverPort = Integer.parseInt(serverProperties
					.getProperty("server.port"));
			websocketPort = Integer.parseInt(serverProperties
					.getProperty("server.websocketPort"));
			charset = serverProperties.getProperty("server.charset");
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

		/**
		 * @return the charset
		 */
		public String getCharset() {
			return charset;
		}

		/**
		 * @return the serverName
		 */
		public String getServerName() {
			return serverName;
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
			shutdownService = ServiceFactoryImpl.getInstance()
					.createShutDownService();
			memcachedService = ServiceFactoryImpl.getInstance().createMemcachedService();

			// 初始化
			httpService.init();
			websocketService.init();
			dbService.init();
			configLoadService.init();
			// propertiesLoadService.init();
			shutdownService.init();
			memcachedService.init();
		}

		private IServerService httpService;
		private IServerService websocketService;
		private IServerService dbService;
		private IServerService configLoadService;
		// private BaseServerService propertiesLoadService;
		/** 停服钩子服务 **/
		private IServerService shutdownService;
		private IServerService memcachedService;
	}

	public static class SeasonSchema {
		private SeasonSchema() {

		}
	}

}
