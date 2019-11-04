package jdbc_learning.i_metadata_test;

import java.util.List;

public class AdminDao extends BaseDao{

    // 删除
    public void delete (int id) {
        String sql = "delete form admin where id=?";
        Object[] paramsValue = {id};
        super.update(sql, paramsValue);
    }

    // 插入
    public void save (Admin admin) {
        String sql = "insert into admin (userName,pwd) values (?, ?)";
        Object[] paramsValue = {admin.getUserName(),admin.getPwd()};
        super.update(sql, paramsValue);
    }

    // 查询全部
    public List<Admin> getAll() {
        String sql = "select * from admin";
        List<Admin> list = super.query(sql, null, Admin.class);
        return list;
    }

    // 根据条件查询(主键)
    public Admin fingById (int id) {
        String sql = "select * from admin where id=?";
        Object[] paramsValue = {id};
        List<Admin> list = super.query(sql, paramsValue, Admin.class);
            return (list != null && list.size() > 0 ? list.get(0) : null);

    }
}
