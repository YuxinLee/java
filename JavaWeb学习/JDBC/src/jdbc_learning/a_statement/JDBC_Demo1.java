package jdbc_learning.a_statement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用Statement对象执行静态sql语句
 *
 */

public class JDBC_Demo1 {
    private String url = "jdbc:mysql://192.168.122.246:3306/database_name";
    private String user = "root";
    private String password = "Passw0rd!";

    /**
     * 执行DDL语句(创建表)
     */
    @Test
    public void test1(){
        Connection connection = null;
        Statement statement = null;
        try{
            //1.驱动注册程序
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接对象
            connection = DriverManager.getConnection(url, user, password);

            //3.创建Statement
            statement = connection.createStatement();

            //4.准备sql
            String sql = "CREATE TABLE teacher(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";

            //5.发送sql语句，执行sql语句,得到返回结果
            int count = statement.executeUpdate(sql);

            //6.输出
            System.out.println("影响了"+count+"行！");
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //7.关闭连接(顺序:后打开的先关闭)
            if(statement!=null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            if(connection!=null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
        }
    }
}
