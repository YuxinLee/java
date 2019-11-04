package emp_sys.dao;

import emp_sys.entity.Admin;

/**
 * 2. 管理员数据访问层接口设计
 */
public interface AdminDao {

    /**
     * 根据用户名密码查询
     * @param admin
     * @return
     */
    Admin findByNameAndPwd(Admin admin);
}
