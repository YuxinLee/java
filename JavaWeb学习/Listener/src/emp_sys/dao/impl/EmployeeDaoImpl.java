package emp_sys.dao.impl;

import emp_sys.dao.EmployeeDao;
import emp_sys.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> getAll() {
        String sql = "select * from employee";
        try {
            return JdbcUtils.getQueryRuner()//
                    .query(sql, new BeanListHandler<Employee>(Employee.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
