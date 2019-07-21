package register_jdbc.service.impl;

import register_jdbc.dao.AdminDao;
import register_jdbc.dao.impl.AdminDaoImpl;
import register_jdbc.entity.Admin;
import register_jdbc.exception.UserExistsException;
import register_jdbc.service.AdminService;

/**
 * 3. 业务逻辑层实现
 *
 */
public class AdminServiceImpl implements AdminService {

    // 调用的dao
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public void register(Admin admin) throws UserExistsException {
        try {
            //1. 先根据用户名查询用户是否存在
            boolean flag = adminDao.userExists(admin.getUserName());

            // 2. 如果用户存在，不允许注册
            if (flag) {
                // 不允许注册, 给调用者提示
                throw new UserExistsException("用户名已经存在，注册失败！");
            }

            // 3. 用户不存在，才可以注册
            adminDao.save(admin);

        } catch (UserExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Admin login(Admin admin) {
        try {
            return adminDao.findByNameAndPwd(admin.getUserName(), admin.getPwd());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
