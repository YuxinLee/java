package a_hello;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {

	@Test
	public void testHello() throws Exception {
		// 对象
		Employee emp = new Employee();
		emp.setEmpName("�೤");
		emp.setWorkDate(new Date());

		// 获取加载配置文件的管理类对象
		Configuration config = new Configuration();
		config.configure();  // 默认加载src/hibenrate.cfg.xml文件
		// 创建session的工厂对象
		SessionFactory sf = config.buildSessionFactory();
		// 创建session (代表一个会话，与数据库连接的会话)
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		//保存-数据库
		session.save(emp);
		// 提交事务
		tx.commit();
		// 关闭
		session.close();
		sf.close();
	}
}
