package contactSys_jdbc.util;

import java.sql.*;

/**
 * jdbc工具类
 *
 */

public class JdbcUtil {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driverClass = null;

    /**
     * 静态代码块中（只加载一次）
     * 信息可以在文件中读取
     */
    static {
        url = "jdbc:mysql://192.168.122.246:3306/database_name";
        user = "root";
        password = "Passw0rd!";
        driverClass = "com.mysql.jdbc.Driver";
        //注册驱动程序
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱程程序注册出错");
        }

    }


    /**
     * 获取连接对象的方法
     */
    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    /**
     * 释放资源的方法
     */
    public static void close(Connection connection, Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
