package emp_sys.service.impl;

import emp_sys.dao.EmployeeDao;
import emp_sys.dao.impl.EmployeeDaoImpl;
import emp_sys.entity.Employee;
import emp_sys.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    @Override
    public List<Employee> getAll() {
        try {
            return employeeDao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
