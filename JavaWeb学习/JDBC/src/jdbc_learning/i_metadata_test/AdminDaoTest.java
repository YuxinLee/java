package jdbc_learning.i_metadata_test;

import org.junit.Test;

import java.util.List;

public class AdminDaoTest {

    @Test
    public void testUpdate() throws Exception {
        AdminDao adminDao = new AdminDao();
        //adminDao.delete(2);
        //adminDao.save(new Admin());

        // 测试查询
        List<Admin> list = adminDao.getAll();
        System.out.println(list);
    }
}
