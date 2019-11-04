package emp_sys.dao;

import emp_sys.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * 查询所有的员工
     */
    List<Employee> getAll();
}
