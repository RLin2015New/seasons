package com.test.service.db.mysql.ibatis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.test.SeasonsStartServer;
import com.test.base.BaseDao;
import com.test.service.base.AbstractBaseServerService;
import com.test.tools.SeasonLogTools;
import com.test.tt.login.dao.TestDaoImpl;

/**
 * @projectName seasons
 * @fileName DBManagerService.java
 * @description
 * @author lifl
 * @time 2018下午3:27:30
 *
 */
/** DB需要考虑要不要单独添加一个启动过程，因为DB的初始化过程相较于其他会更复杂 **/
/**
 * 
 * v1.单一静态数据源 2018年1月9日15:57:10<br>
 * v2.TODO 数据库连接池
 * 
 */
public class IbatisService extends AbstractBaseServerService {

	@Override
	public void init() throws IOException {
		setState(ServerState.starting);
		SeasonLogTools.getLog().info("开始初始化ibatisService服务");
		FileInputStream fis = new FileInputStream(SeasonsStartServer.configRoot
				+ "jdbc.properties");
		Properties properties = new Properties();
		properties.load(fis);
		fis.close();
		/**
		 * db相关的表配置，放在src内比src外更优，因为必要时可以隐藏实现
		 */
		Reader reader = Resources
				.getResourceAsReader(SeasonsStartServer.ibatisRoot
						+ "sqlMapConfig.xml");
		BaseDao.initSqlMap(SqlMapClientBuilder.buildSqlMapClient(reader,
				properties));
		reader.close();

		try {
			System.out.println("测试连接:"+TestDaoImpl.getInstance().ibatisTestSelect(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setState(ServerState.started);
	}

	@Override
	public void stop() {
		setState(ServerState.stoped);
	}

}
