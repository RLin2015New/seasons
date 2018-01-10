package com.test.web.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.tools.TimeTools;

/**
 * @projectName seasons
 * @fileName WebSocketFactoryConfiguration.java
 * @description
 * @author lifl
 * @time 2018下午2:34:00
 *
 */

public class WebSocketFactoryConfiguration extends WebSocketServlet {

	private static long IdleTime = 20 * TimeTools.SECOND;

	private static final long serialVersionUID = -7484885952817818371L;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(IdleTime);
		factory.register(WebSocketListenerImpl.class);
	}

}
