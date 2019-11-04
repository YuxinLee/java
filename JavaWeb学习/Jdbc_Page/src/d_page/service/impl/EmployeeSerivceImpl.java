package d_page.service.impl;

import d_page.dao.EmployeeDao;
import d_page.dao.impl.EmployeeDaoImpl;
import d_page.entity.Employee;
import d_page.service.EmployeeService;
import d_page.utils.PageBean;

/**
 * 3. 业务逻辑层，实现
 */
public class EmployeeSerivceImpl implements EmployeeService {

    // 创建Dao实例
    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void getAll(PageBean<Employee> pb) {
        try {
            employeeDao.getAll(pb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
