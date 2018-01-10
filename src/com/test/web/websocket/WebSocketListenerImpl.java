package com.test.web.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import com.test.tools.SeasonLogTools;

/**
 * @projectName seasons
 * @fileName WebSocketListenerImpl.java
 * @description
 * @author lifl
 * @time 2018下午3:21:54
 *
 */

public class WebSocketListenerImpl implements WebSocketListener {

	@Override
	public void onWebSocketBinary(byte payload[], int offset, int len) {
		SeasonLogTools.getLog().info("onWebSocketBinary");
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		SeasonLogTools.getLog().info("onWebSocketClose "+statusCode+"_"+reason);
	}

	@Override
	public void onWebSocketConnect(Session session) {
		SeasonLogTools.getLog().info("onWebSocketConnect");
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		SeasonLogTools.getLog().info("onWebSocketError"+cause);
	}

	@Override
	public void onWebSocketText(String message) {
		SeasonLogTools.getLog().info("onWebSocketText"+message);
	}

}
