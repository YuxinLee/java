package emp_sys.service;

import emp_sys.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 查询所有的员工
     */
    List<Employee> getAll();
}
