package b_dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * DBCP 是 Apache 软件基金组织下的开源连接池实现，使用DBCP数据源，应用程序应在系统中增加如下两个 jar 文件：
 * Commons-dbcp.jar：连接池的实现
 * Commons-pool.jar：连接池实现的依赖库
 * Tomcat 的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用。
 * 核心类：BasicDataSource
 * 使用步骤
 * 引入jar文件
 * commons-dbcp-1.4.jar
 * commons-pool-1.5.6.jar
 *
 */
public class DBCP_Demo1 {

    // 1. 硬编码方式实现连接池
    @Test
    public void testDbcp() throws Exception {
        // DBCP连接池核心类
        BasicDataSource dataSouce = new BasicDataSource();

        // 连接池参数配置：初始化连接数、最大连接数 / 连接字符串、驱动、用户、密码
        dataSouce.setUrl("jdbc:mysql:///jdbc_demo");    //数据库连接字符串
        dataSouce.setDriverClassName("com.mysql.jdbc.Driver");  //数据库驱动
        dataSouce.setUsername("root");      //数据库连接用户
        dataSouce.setPassword("root");      //数据库连接密码
        dataSouce.setInitialSize(3);  // 初始化连接
        dataSouce.setMaxActive(6);	  // 最大连接
        dataSouce.setMaxIdle(3000);   // 最大空闲时间

        // 获取连接
        Connection con = dataSouce.getConnection();
        con.prepareStatement("delete from admin where id=3").executeUpdate();
        con.close();  // 关闭
    }

    @Test
    // 2. 【推荐】配置方式实现连接池  ,  便于维护
    public void testProp() throws Exception {
        // 加载prop配置文件
        Properties prop = new Properties();

        // 获取文件流
        InputStream inStream = DBCP_Demo1.class.getResourceAsStream("db.properties");

        // 加载属性配置文件
        prop.load(inStream);

        // 根据prop配置，直接创建数据源对象
        DataSource dataSouce = BasicDataSourceFactory.createDataSource(prop);

        // 获取连接
        Connection con = dataSouce.getConnection();
        con.prepareStatement("delete from admin where id=4").executeUpdate();
        con.close();    // 关闭
    }
}
