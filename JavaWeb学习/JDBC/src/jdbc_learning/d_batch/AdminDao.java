package jdbc_learning.d_batch;

import jdbc_learning.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 封装所有的与数据库的操作
 */
public class AdminDao {
    // 全局参数
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 批量保存管理员
    public void save(List<Admin> list) {
        // SQL
        String sql = "INSERT INTO admin(userName,pwd) values(?,?)";

        try {
            // 获取连接
            con = JdbcUtil.getConnection();

            // 创建stmt
            pstmt = con.prepareStatement(sql);

            for (int i=0; i<list.size(); i++) {
                Admin admin = list.get(i);
                // 设置参数
                pstmt.setString(1, admin.getUserName());
                pstmt.setString(2, admin.getPwd());

                // 添加批处理
                pstmt.addBatch();

                // 测试：每5条执行一次批处理
                if (i % 5 == 0) {
                    // 批量执行
                    pstmt.executeBatch();
                    // 清空批处理
                    pstmt.clearBatch();
                }
            }

            // 批量执行
            pstmt.executeBatch();
            // 清空批处理
            pstmt.clearBatch();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(con, pstmt, rs);
        }
    }
}
