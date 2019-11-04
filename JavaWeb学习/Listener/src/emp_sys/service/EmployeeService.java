package emp_sys.service;

import emp_sys.entity.Employee;

import java.util.List;

/**
 * 2. 员工业务逻辑层
 */
public interface EmployeeService {

    /**
     * 查询所有的员工
     * @return
     */
    List<Employee> getAll();
}
