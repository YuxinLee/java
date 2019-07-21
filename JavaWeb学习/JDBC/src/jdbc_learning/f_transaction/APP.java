package jdbc_learning.f_transaction;

import org.junit.Test;

public class APP {

    @Test
    public void testname() throws Exception {

        // 转账
        AccountDao accountDao = new AccountDao();
        accountDao.trans();
    }
}
