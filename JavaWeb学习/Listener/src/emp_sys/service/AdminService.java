package emp_sys.service;

import emp_sys.entity.Admin;

/**
 * 3. 管理员业务逻辑层
 *
 */
public interface AdminService {

    /**
     * 根据用户名密码查询
     * @param admin
     * @return
     */
    Admin findByNameAndPwd(Admin admin);
}
