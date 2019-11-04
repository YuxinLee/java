package c_hbm_config;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App2 {

	private static SessionFactory sf;
	static  {
		// 创建sf对象
		sf = new Configuration()
				.configure()
				.addClass(Employee.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
				.buildSessionFactory();
	}

	//1. 保存对象
	@Test
	public void testSave() throws Exception {
		// 对象
		Employee emp = new Employee();
		emp.setEmpName("张三");
		emp.setWorkDate(new Date());
		emp.setDesc("描述");

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(emp);

		tx.commit();
		session.close();
	}

}











