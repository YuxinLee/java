package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class JdbcUtils {

    // 初始化连接池,默认初始化c3p0-config.xml文件
    private static DataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    //外界获取dataSource的方法
    public static DataSource getDataSource() {
        return dataSource;
    }

    //创建DbUtils常用工具类对象
    public static QueryRunner getQueryRunner() {
        return new QueryRunner(dataSource);
    }
}
