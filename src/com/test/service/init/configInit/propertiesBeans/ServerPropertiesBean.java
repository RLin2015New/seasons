package com.test.service.init.configInit.propertiesBeans;


/**
 * @projectName seasons
 * @fileName ServerProperties.java
 * @description
 * @author lifl
 * @time 2018下午4:16:43
 *
 */

public class ServerPropertiesBean extends BasePropertiesBean {

	private int server_id;
	private String server_type;
	private int server_port;
	private int server_websocketPort;

	public int getServer_id() {
		return server_id;
	}

	public String getServer_type() {
		return server_type;
	}

	public int getServer_port() {
		return server_port;
	}

	public int getServer_websocketPort() {
		return server_websocketPort;
	}

	public void setServer_id(int server_id) {
		this.server_id = server_id;
	}

	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}

	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}

	public void setServer_websocketPort(int server_websocketPort) {
		this.server_websocketPort = server_websocketPort;
	}

}
