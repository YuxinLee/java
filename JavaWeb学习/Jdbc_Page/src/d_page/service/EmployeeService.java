package d_page.service;

import d_page.entity.Employee;
import d_page.utils.PageBean;

/**
 * 3. 业务逻辑层接口设计
 */
public interface EmployeeService {

    /**
     * 分页查询数据
     */
    public void getAll(PageBean<Employee> pb);
}
