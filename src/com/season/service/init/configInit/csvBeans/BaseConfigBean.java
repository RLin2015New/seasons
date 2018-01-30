package com.season.service.init.configInit.csvBeans;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.season.tools.SeasonLogTools;
import com.tools.CommonTools;
import com.tools.StringTools;

/**
 * @projectName seasons
 * @fileName BaseConfigBean.java
 * @description
 * @author lifl
 * @time 2018下午4:24:15
 *
 */
/** 离优雅差了一个叫做 泛型的东东~ **/
public abstract class BaseConfigBean {

	public abstract List<? extends BaseConfigBean> getBeansByFile(
			String filePath, boolean includeHeader) throws Exception;

	/**
	 * 
	 * @description
	 * @param c
	 * @param keys
	 * @param values
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 *             BaseConfigBean
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @time 2018上午10:22:51
	 */
	public BaseConfigBean getBeanByLine(Class<? extends BaseConfigBean> c,
			String[] keys, String[] values) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, ParseException {
		BaseConfigBean bean = c.newInstance();
		if (keys.length != values.length) {
			throw new NullPointerException("参数行长度与标题不匹配" + values);
		}
		Field[] fs = c.getDeclaredFields();
		for (int i = 0; i < keys.length; i++) {
			boolean has = false;
			for (int j = 0; j < fs.length; j++) {
				Field field = fs[j];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (field.getName().equals(keys[i])) {
					has = true;
					String type = field.getType().toString();
					String value = values[i];
					/**
					 * 当前只支持string，int，long，float，double，bool，date（yyyy-MM-dd）
					 * **/
					field.setAccessible(true);// 修改private 暴力耶 囧~
					if (type.endsWith("String")) {
						field.set(bean, value);
					} else if (type.endsWith("int") || type.endsWith("Integer")) {
						field.set(bean, Integer.valueOf(value));
					} else if (type.endsWith("long") || type.endsWith("Long")) {
						field.set(bean, Long.valueOf(value));
					} else if (type.endsWith("float") || type.endsWith("Float")) {
						field.set(bean, Float.valueOf(value));
					} else if (type.endsWith("double")
							|| type.endsWith("Double")) {
						field.set(bean, Double.valueOf(value));
					} else if (type.toLowerCase().endsWith("bool")
							|| type.toLowerCase().endsWith("boolean")) {
						if (!"true".equals(value.toLowerCase())
								&& !"false".equals(value.toLowerCase())) {
							throw new NullPointerException("行参数布尔值不符合规范:"
									+ value + "_" + values);
						}
						field.set(bean, StringTools.getBoolean(value, false));
					} else if (type.endsWith("date") || type.endsWith("Date")) {
						field.set(bean, sdf.parse(value));
					}else{
						has = false;
					}
				}
			}
			if (!has) {
				throw new NullPointerException("存在没有找到匹配的参数列或类型不支持:" + keys[i] + "_"
						+ c);
			}
		}
		SeasonLogTools.getLog().info("" + CommonTools.writeValueAsString(bean));
		return bean;
	}
}
