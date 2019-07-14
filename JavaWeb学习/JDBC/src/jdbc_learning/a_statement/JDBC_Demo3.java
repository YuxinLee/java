package jdbc_learning.a_statement;

import jdbc_learning.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用Statement执行DQL语句（查询操作）
 *
 */
public class JDBC_Demo3 {
    @Test
    public void test1(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //获取连接
            conn = JdbcUtil.getConnection();
            //创建Statement
            stmt = conn.createStatement();
            //准备sql
            String sql = "SELECT * FROM student";
            //执行sql
            rs = stmt.executeQuery(sql);

            //移动光标
			/*boolean flag = rs.next();

			flag = rs.next();
			flag = rs.next();
			if(flag){
				//取出列值
				//索引
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				System.out.println(id+","+name+","+gender);

				//列名称
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				System.out.println(id+","+name+","+gender);
			}*/

            //遍历结果
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                System.out.println(id+","+name+","+gender);
            }

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            JdbcUtil.close(conn, stmt, rs);
        }
    }
}
