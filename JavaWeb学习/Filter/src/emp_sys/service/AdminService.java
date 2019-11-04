package emp_sys.service;

import emp_sys.entity.Admin;

public interface AdminService {

    /**
     * 根据用户名密码查询
     */
    Admin findByNameAndPwd(Admin admin);
}
