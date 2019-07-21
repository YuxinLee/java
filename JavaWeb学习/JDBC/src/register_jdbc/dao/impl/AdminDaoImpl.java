package register_jdbc.dao.impl;

import register_jdbc.dao.AdminDao;
import register_jdbc.entity.Admin;
import register_jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 数据访问层接口的实现类
 *
 */
public class AdminDaoImpl implements AdminDao {

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void save(Admin admin) {
        String sql = "INSERT INTO admin (userName, pwd) VALUES(?, ?)";
        try {
            con = JdbcUtil.getConnection();
            pstmt = con.prepareStatement(sql);

            //设置参数
            pstmt.setString(1, admin.getUserName());
            pstmt.setString(2, admin.getPwd());

            //执行更新
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, null);
        }

    }

    @Override
    public Admin findByNameAndPwd(String name, String pwd) {
        String sql = "select * from admin where userName=? and pwd=?";
        Admin ad = null;
        try {
            con = JdbcUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            // 执行
            rs = pstmt.executeQuery();
            // 遍历
            if (rs.next()) {
                ad = new Admin();
                ad.setId(rs.getInt("id"));
                ad.setUserName(rs.getString("userName"));
                ad.setPwd(rs.getString("pwd"));
            }
            return ad;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }

    @Override
    public boolean userExists(String name) {
        String sql = "select id from admin where userName=?";
        try {
            con = JdbcUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            // 设置参数
            pstmt.setString(1, name);
            // 执行查询
            rs = pstmt.executeQuery();
            // 判断
            if (rs.next()) {
                int id = rs.getInt("id");
                if (id > 0) {
                    // 用户名已经存在
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.closeAll(con, pstmt, rs);
        }
    }
}
