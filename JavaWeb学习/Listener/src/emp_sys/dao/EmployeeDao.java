package emp_sys.dao;

import emp_sys.entity.Employee;

import java.util.List;

/**
 * 2. 员工数据访问层接口设计
 */
public interface EmployeeDao {

    /**
     * 查询所有的员工
     * @return
     */
    List<Employee> getAll();
}
