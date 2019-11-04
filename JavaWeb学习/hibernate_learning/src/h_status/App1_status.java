package cn.itcast.a_status;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App1_status {

	private static SessionFactory sf;
	static {
		sf = new Configuration()
				.configure()
				.addClass(User.class)   // 测试时候使用
				.buildSessionFactory();
	}

	//1. 对象状态的转换
	@Test
	public void testSaveSet() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		// 创建对象						【临时状态】
//		User user = new User();
//		user.setUserName("Jack22222");
		// 保存							【持久化状态】
//		session.save(user);
//		user.setUserName("Jack333333");  // 会反映到数据库


		// 查询
		User user = (User) session.get(User.class, 5);
		user.setUserName("Tomcat");// hibernate会自动与数据库匹配（一级缓存），如果一样就更新数据库

		session.getTransaction().commit();
		session.close();


		user.setUserName("Jack444444444");
		// 打印							【游离状态】
		System.out.println(user.getUserId());
		System.out.println(user.getUserName());
	}


	@Test
	public void bak() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
		session.close();
	}
}









