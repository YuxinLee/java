package register_jdbc.service;

import register_jdbc.entity.Admin;
import register_jdbc.exception.UserExistsException;

/**
 * 业务逻辑层接口设计
 *
 */
public interface AdminService {

    /**
     * 注册
     */
    void register(Admin admin) throws UserExistsException;

    /**
     * 登陆
     */
    Admin login(Admin admin);
}
