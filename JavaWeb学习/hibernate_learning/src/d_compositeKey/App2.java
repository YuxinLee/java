package cn.itcast.d_compositeKey;

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
				.addClass(User.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
				.buildSessionFactory();
	}

	//1. 保存对象
	@Test
	public void testSave() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		// 对象
		CompositeKeys keys = new CompositeKeys();
		keys.setAddress("广州棠东");
		keys.setUserName("Jack");
		User user = new User();
		user.setAge(20);
		user.setKeys(keys);

		// 保存
		session.save(user);


		tx.commit();
		session.close();
	}

	@Test
	public void testGet() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		//构建主键再查询
		CompositeKeys keys = new CompositeKeys();
		keys.setAddress("广州棠东");
		keys.setUserName("Jack");

		// 主键查询
		User user = (User) session.get(User.class, keys);
		// 测试输出
		if (user != null){
			System.out.println(user.getKeys().getUserName());
			System.out.println(user.getKeys().getAddress());
			System.out.println(user.getAge());
		}


		tx.commit();
		session.close();
	}
}











