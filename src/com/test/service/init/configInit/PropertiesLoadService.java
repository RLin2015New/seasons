package com.test.service.init.configInit;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;

import com.test.SeasonsStartServer;
import com.test.service.base.AbstractBaseServerService;
import com.test.service.init.configInit.propertiesBeans.BasePropertiesBean;
import com.test.service.init.configInit.propertiesBeans.ServerPropertiesBean;
import com.test.tools.SeasonLogTools;
import com.tools.NyTools;
import com.tools.StringTools;

/**
 * @projectName seasons
 * @fileName PropertiesLoadService.java
 * @description
 * @author lifl
 * @time 2018下午3:45:10
 *
 */
/** 这种实现好像比较鸡肋，因為properties文件一般很少 **/
public class PropertiesLoadService extends AbstractBaseServerService {
	private ServerPropertiesBean serverProperties;

	@Override
	public void init() throws Exception {
		SeasonLogTools.getLog().info("开始初始化PropertiesLoadService服务");
		setState(ServerState.starting);
		serverProperties = (ServerPropertiesBean) getBeanByProperties(
				ServerPropertiesBean.class,
				getProperties(SeasonsStartServer.configRoot
						+ "server.properties"));
		setState(ServerState.started);
	}

	@Override
	public void stop() {
		setState(ServerState.stoped);
	}

	public static Properties getProperties(String path) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(path);
		properties.load(fis);
		fis.close();
		return properties;
	}

	public static BasePropertiesBean getBeanByProperties(
			Class<? extends BasePropertiesBean> c, Properties property)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, ParseException {
		if (property == null) {
			throw new NullPointerException("property 为空");
		}
		Enumeration<Object> keys = property.keys();
		BasePropertiesBean bean = c.newInstance();
		Field[] fs = c.getDeclaredFields();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			String keyName = key.toString().replace(".", "_");
			boolean has = false;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Field f : fs) {
				if (keyName.equals(f.getName())) {
					f.setAccessible(true);
					String value = property.getProperty(key.toString());
					String type = f.getType().toString();
					has = true;
					if (type.toLowerCase().endsWith("string")) {
						f.set(bean, value);
					} else if (type.toLowerCase().endsWith("int")) {
						f.set(bean, Integer.parseInt(value));
					} else if (type.toLowerCase().endsWith("long")) {
						f.set(bean, Long.parseLong(value));
					} else if (type.toLowerCase().endsWith("float")) {
						f.set(bean, Float.parseFloat(value));
					} else if (type.toLowerCase().endsWith("double")) {
						f.set(bean, Double.parseDouble(value));
					} else if (type.toLowerCase().endsWith("bool")
							|| type.toLowerCase().endsWith("boolean")) {
						if (!"true".equals(value.toLowerCase())
								&& !"false".equals(value.toLowerCase())) {
							throw new NullPointerException("参数布尔值不符合规范:" + key
									+ ":" + value);
						}
						f.set(bean, StringTools.getBoolean(value, false));
					} else if (type.toLowerCase().endsWith("date")) {
						f.set(bean, sdf.parse(value));
					} else {
						has = false;
					}

				}
			}
			if (!has) {
				throw new NullPointerException("存在没有找到匹配的参数列或类型不支持:" + key
						+ "_" + property.getProperty(key.toString()));
			}
		}
		SeasonLogTools.getLog().info(
				"property:" + NyTools.writeValueAsString(bean));
		return bean;
	}

	/**
	 * @return the serverProperties
	 */
	public ServerPropertiesBean getServerProperties() {
		return serverProperties;
	}

}
