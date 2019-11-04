package emp_sys.dao;

import emp_sys.entity.Admin;

public interface AdminDao {

    /**
     * 根据用户名密码查询
     * @param admin
     * @return Admin
     */

    Admin findByNameAndPwd(Admin admin);
}
