package com.season.web.websocket.base;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.season.web.websocket.WebSocketFactory;
import com.tools.TimeTools;

/**
 * @projectName seasons
 * @fileName WebSocketFactoryConfiguration.java
 * @description
 * @author lifl
 * @time 2018下午2:34:00
 *
 */

public class WebSocketConfiguration extends WebSocketServlet {
	private static final long serialVersionUID = -7484885952817818371L;
	private static long IdleTime = 20 * TimeTools.SECOND;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(IdleTime);
		factory.register(WebSocketFactory.getInstance().getListenerClas());
	}

}
