package register_jdbc.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import register_jdbc.entity.Admin;
import register_jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 2. 数据访问层接口的实现类
 * (引入DbUtils组件简化jdbc操作)
 */
public class AdminDaoImpl2 {

    private Connection con;
    private QueryRunner qr = new QueryRunner();

    public Admin findByNameAndPwd(Admin admin) {
        String sql = "select * from admin where userName=? and pwd=?";
        try {
            con = JdbcUtil.getConnection();
            Admin ad = qr.query(con, sql,
                    new BeanHandler<Admin>(Admin.class),
                    admin.getUserName(),
                    admin.getPwd());
            return ad;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, null, null);
        }
    }

    public void save(Admin admin) {
        String sql = "INSERT INTO admin(userName,pwd) VALUES(?,?);";
        try {
            con = JdbcUtil.getConnection();
            // 使用DbUtils组件的方法更新
            qr.update(con, sql, admin.getUserName(),admin.getPwd());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, null, null);
        }
    }

    public boolean userExists(String name) {
        String sql = "select id from admin where userName=?";
        try {
            con = JdbcUtil.getConnection();
            Integer in = qr.query(con, sql, new ScalarHandler<Integer>(), name);
            if (in != null){
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, null, null);
        }
    }
}
