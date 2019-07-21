package register_jdbc.dao;

import register_jdbc.entity.Admin;

/**
 * 数据访问层, 接口设计
 *
 */
public interface AdminDao {

    //保存
    void save (Admin admin);

    //根据用户名密码查询
    Admin findByNameAndPwd(String name, String Pwd);

    //检查用户名是否存在
    boolean userExists(String name);
}
