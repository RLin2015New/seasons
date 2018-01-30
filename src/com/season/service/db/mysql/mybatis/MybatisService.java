package com.season.service.db.mysql.mybatis;

import static com.season.SeasonsStartServer.configRoot;
import static com.season.SeasonsStartServer.mabatisRoot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;
import com.season.base.BaseDao;
import com.season.sample.loginTest.dao.TestDaoImpl;
import com.season.service.base.AbstractBaseServerService;

/**
 * @projectName seasons
 * @fileName MybatisService.java
 * @description
 * @author lifl
 * @time 2018下午2:58:32
 *
 */

public class MybatisService extends AbstractBaseServerService {

	@Override
	public void init() throws Exception {
		/**
		 * 关于文件读取。src内的资源文件，一版是借助于classloader定位，跟着class文件走的，所以直接用相对路径就OK
		 * 而src外的文件，不跟着class走，通常用URL来定位，通常用 file:路径 格式访问
		 */
		InputStream inputStream = Resources
				.getResourceAsStream(mabatisRoot
						+ "mybatis-config.xml");
		// InputStream inputStream = Resources.getUrlAsStream("file:"
		// + SeasonsStartServer.mabatisRoot + "mybatis-config.xml");
		/**
		 * 第一种初始化方式：直接在xml中写属性，要留意url的参数写法<br>
		 * SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
		 * .build(inputStream); <br>
		 * 范例:<br>
		 * <dataSource type="POOLED"><br>
		 * <property name="driver" value="com.mysql.jdbc.Driver" /><br>
		 * <property name="url" value=
		 * "jdbc:mysql://localhost:3306/niuyouba?;zeroDateTimeBehavior=round;useUnicode=true;characterEncoding=utf-8"
		 * /><br>
		 * <property name="username" value="root" /><br>
		 * <property name="password" value="123456" /><br>
		 * </dataSource><br>
		 */
		/**
		 * 第二种初始化方式：从properties文件中读取
		 */
		Properties jdbcProperties = queryServerConf(configRoot
				+ "jdbc.properties");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream, jdbcProperties);
		BaseDao.initSqlFactory(sqlSessionFactory);
		/**
		 * 第一种访问方式
		 */
		System.out.println("测试连接01:"+TestDaoImpl.getInstance().mybatisTestSelect01(1));
		/**
		 * 第二种访问方式
		 */
		System.out.println("测试连接02:"+TestDaoImpl.getInstance().mybatisTestSelect02(1));
	}

	public Properties queryServerConf(String path) throws IOException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(path);
		properties.load(fis);
		fis.close();
		return properties;
	}

	@Override
	public void stop() {

	}

}
