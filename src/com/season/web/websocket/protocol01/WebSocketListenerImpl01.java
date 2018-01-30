package com.season.web.websocket.protocol01;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import com.season.tools.SeasonLogTools;
import com.season.web.websocket.WebSocketFactory;
import com.season.web.websocket.base.IDecoder;
import com.season.web.websocket.base.IMessageUnit;
import com.season.web.websocket.base.IWebSocketUser;

/**
 * @projectName seasons
 * @fileName WebSocketListenerImpl.java
 * @description
 * @author lifl
 * @time 2018下午3:21:54
 *
 */

public class WebSocketListenerImpl01 implements WebSocketListener {
	private IWebSocketUser user;
	private WebSocketBinaryHandler handler = new WebSocketBinaryHandler();

	@Override
	public void onWebSocketBinary(byte payload[], int offset, int len) {
		SeasonLogTools.getLog().info("onWebSocketBinary");
		IDecoder decoder = WebSocketFactory.getInstance().generateDecoder();
		IMessageUnit unit = decoder.decode(getUser(), payload);
		handler.handler(user, unit);
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		SeasonLogTools.getLog().info(
				"onWebSocketClose " + statusCode + "_" + reason);
	}

	@Override
	public void onWebSocketConnect(Session session) {
		SeasonLogTools.getLog().info("onWebSocketConnect");
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		SeasonLogTools.getLog().info("onWebSocketError" + cause);
	}

	@Override
	public void onWebSocketText(String message) {
		SeasonLogTools.getLog().info("onWebSocketText" + message);
	}

	/**
	 * @return the user
	 */
	public IWebSocketUser getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(IWebSocketUser user) {
		this.user = user;
	}

}
