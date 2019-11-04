package d_page.dao;

import d_page.entity.Employee;
import d_page.utils.PageBean;

/**
 * 2. 数据访问层，接口设计
 *
 */
public interface EmployeeDao {

    /**
     * 分页查询数据
     */
    public void getAll(PageBean<Employee> pb);

    /**
     * 查询总记录数
     */
    public int getTotalCount();
}
