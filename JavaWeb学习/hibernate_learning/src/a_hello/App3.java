package a_hello;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class App3 {

	private static SessionFactory sf;
	static  {

		// 创建sf对象
		sf = new Configuration().configure().buildSessionFactory();
	}

	//HQL查询  【适合有数据库基础的】
	@Test
	public void testQuery() throws Exception {

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		// 主键查询
		//Employee emp = (Employee) session.get(Employee.class, 1);

		// HQL查询，查询全部
		Query q = session.createQuery("from Employee where empId=1 or empId=2");
		List<Employee> list = q.list();

		System.out.println(list);

		tx.commit();
		session.close();
	}


	//QBC查询  , query by criteria  完全面向对象的查询
	@Test
	public void testQBC() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Employee.class);
		// 条件
		criteria.add(Restrictions.eq("empId", 1));
		// 查询全部
		List<Employee> list = criteria.list();

		System.out.println(list);

		tx.commit();
		session.close();
	}

	//sQL
	@Test
	public void testSQL() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		// 把每一行记录封装为对象数组，再添加到list集合
//		SQLQuery sqlQuery = session.createSQLQuery("select * from employee");
		// 把每一行记录封装为 指定的对象类型
		SQLQuery sqlQuery = session.createSQLQuery("select * from employee").addEntity(Employee.class);
		List list = sqlQuery.list();

		System.out.println(list);

		tx.commit();
		session.close();
	}
}











