package c_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C3P0_Demo1 {

    @Test
    //1. 硬编码方式，使用C3P0连接池管理连接
    public void testCode() throws Exception {
        // 创建连接池核心工具类
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // 设置连接参数：url、驱动、用户密码、初始连接数、最大连接数
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_demo");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setInitialPoolSize(3);
        dataSource.setMaxPoolSize(6);
        dataSource.setMaxIdleTime(1000);

        // ---> 从连接池对象中，获取连接对象
        Connection con = dataSource.getConnection();

        // 执行更新
        con.prepareStatement("delete from admin where id=7").executeUpdate();

        // 关闭
        con.close();
    }

    @Test
    //2. XML配置方式，使用C3P0连接池管理连接
    public void testXML() throws Exception {
        // 创建c3p0连接池核心工具类
        // 自动加载src下c3p0的配置文件【c3p0-config.xml】
        ComboPooledDataSource dataSource = new ComboPooledDataSource();// 使用默认的配置
        PreparedStatement pstmt = null;

        // 获取连接
        Connection con = dataSource.getConnection();
        for (int i=1; i<11;i++){
            String sql = "insert into employee(empName,dept_id) values(?,?)";
            // 执行更新
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "Rose" + i);
            pstmt.setInt(2, 1);
            pstmt.executeUpdate();
        }
        pstmt.close();
        // 关闭
        con.close();

    }
}
