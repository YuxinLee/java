package cn.itcast.a_status;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App3_list_iterator {

	private static SessionFactory sf;
	static {
		sf = new Configuration()
				.configure()
				.addClass(User.class)   // 测试时候使用
				.buildSessionFactory();
	}
	/**
	 * list与iterator区别
	 * 1. list 方法
	 * 2. iterator 方法
	 * 3. 缓存
	 * @throws Exception
	 */
	//1.  list 方法
	@Test
	public void list() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		// HQL查询
		Query q = session.createQuery("from User ");
		// list()方法
		List<User> list = q.list();

		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}

		session.getTransaction().commit();
		session.close();
	}

	//2. iterator 方法
	@Test
	public void iterator() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();
		// HQL查询
		Query q = session.createQuery("from User ");
		// iterator()方法
		Iterator<User> it = q.iterate();
		while(it.hasNext()){
			// 得到当前迭代的每一个对象
			User user = it.next();
			System.out.println(user);
		}



		session.getTransaction().commit();
		session.close();
	}


	//3. 缓存
	@Test
	public void cache() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		/**************执行2次list*****************
		 Query q = session.createQuery("from User");
		 List<User> list = q.list();      // 【会放入？】
		 for (int i=0; i<list.size(); i++){
		 System.out.println(list.get(i));
		 }
		 System.out.println("=========list===========");
		 list = q.list();				// 【会放入?】
		 for (int i=0; i<list.size(); i++){
		 System.out.println(list.get(i));
		 }

		 /**************执行2次iteator******************/
		Query q = session.createQuery("from User ");
		Iterator<User> it = q.iterate();		// 【放入缓存】
		while(it.hasNext()){
			User user = it.next();
			System.out.println(user);
		}
		System.out.println("==========iterate===========");
		it = q.iterate();						// 【也会从缓存中取】
		while(it.hasNext()){
			User user = it.next();
			System.out.println(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	// 测试list方法会放入缓存
	@Test
	public void list_iterator() throws Exception {
		Session session = sf.openSession();
		session.beginTransaction();

		// 得到Query接口的引用
		Query q = session.createQuery("from User ");

		// 先list  【会放入缓存，但不会从缓存中获取数据】
		List<User> list = q.list();
		for (int i=0; i<list.size(); i++){
			System.out.println(list.get(i));
		}

		// 再iteraotr  (会从缓存中取)
		Iterator<User> it = q.iterate();
		while(it.hasNext()){
			User user = it.next();
			System.out.println(user);
		}

		session.getTransaction().commit();
		session.close();
	}
}









