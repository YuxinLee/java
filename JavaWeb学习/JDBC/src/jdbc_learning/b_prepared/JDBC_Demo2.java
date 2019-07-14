package jdbc_learning.b_prepared;

import jdbc_learning.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 模拟用户登录效果
 *
 */
public class JDBC_Demo2 {
    //模拟用户输入
    //private String name = "ericdfdfdfddfd' OR 1=1 -- ";
    private String name = "eric";
    //private String password = "123456dfdfddfdf";
    private String password = "123456";
    /**
     * Statment存在sql被注入的风险
     */
    @Test
    public void testByStatement(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();

            //创建Statment
            stmt = conn.createStatement();

            //准备sql
            String sql = "SELECT * FROM users WHERE NAME='"+name+"' AND PASSWORD='"+password+"'";

            //执行sql
            rs = stmt.executeQuery(sql);

            if(rs.next()){
                //登录成功
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, stmt ,rs);
        }

    }

    /**
     * PreparedStatement可以有效地防止sql被注入
     */
    @Test
    public void testByPreparedStatement(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();

            String sql = "SELECT * FROM users WHERE NAME=? AND PASSWORD=?";

            //预编译
            stmt = conn.prepareStatement(sql);

            //设置参数
            stmt.setString(1, name);
            stmt.setString(2, password);

            //执行sql
            rs = stmt.executeQuery();

            if(rs.next()){
                //登录成功
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, stmt ,rs);
        }

    }
}
