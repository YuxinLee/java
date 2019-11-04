package emp_sys.service.impl;

import emp_sys.dao.AdminDao;
import emp_sys.dao.impl.AdminDaoImpl;
import emp_sys.entity.Admin;
import emp_sys.service.AdminService;

public class AdminServiceImpl implements AdminService {

    // 创建dao对象
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin findByNameAndPwd(Admin admin) {
        try {
            return adminDao.findByNameAndPwd(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
