package emp_sys.dao.impl;

import emp_sys.dao.AdminDao;
import emp_sys.entity.Admin;
import emp_sys.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin findByNameAndPwd(Admin admin) {
        try {
            String sql = "select * from admin where userName=? and pwd=?";
            return JdbcUtils.getQueryRuner()
                    .query(sql,
                            new BeanHandler<Admin>(Admin.class),
                            admin.getUserName(),
                            admin.getPwd());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
