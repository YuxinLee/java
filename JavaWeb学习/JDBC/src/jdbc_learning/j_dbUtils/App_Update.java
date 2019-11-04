package jdbc_learning.j_dbUtils;

import jdbc_learning.util.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;

public class App_Update {

    private Connection conn;

    // 1. 更新
    @Test
    public void testUpdate() throws Exception {
        String sql = "delete from admin where id=?";

        // 连接对象
        conn = JdbcUtil.getConnection();

        // 创建DbUtils核心工具类对象
        QueryRunner qr = new QueryRunner();
        qr.update(conn, sql, 26);

        // 关闭
        DbUtils.close(conn);
    }

    // 2. 批处理
    @Test
    public void testBatch() throws Exception {
        String sql = "insert into admin (userName, pwd) values(?,?)";
        conn = JdbcUtil.getConnection();
        QueryRunner qr = new QueryRunner();

        // 批量删除
        qr.batch(conn, sql, new Object[][]{ {"jack1","888"},{"jack2","999"}  });

        // 关闭
        conn.close();
    }
}
