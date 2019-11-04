package d_page.dao.impl;

import d_page.dao.EmployeeDao;
import d_page.entity.Employee;
import d_page.utils.JdbcUtils;
import d_page.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * 2. 数据访问层实现
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void getAll(PageBean<Employee> pb) {

    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from employee";
        try {
            // 创建QueryRunner对象
            QueryRunner qr = JdbcUtils.getQueryRuner();

            // 执行查询， 返回结果的第一行的第一列
            Long count = qr.query(sql, new ScalarHandler<Long>());//****用long类型。不能用Integer类型，会出问题****
            return count.intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
