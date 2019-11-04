package cn.itcast.a_query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App_page {

	private static SessionFactory sf;
	static {
		sf = new Configuration()
				.configure()
				.addClass(Dept.class)
				.addClass(Employee.class)   // 测试时候使用
				.buildSessionFactory();
	}

	// 分页查询
	@Test
	public void all() {

		Session session = sf.openSession();
		session.beginTransaction();

		Query q = session.createQuery("from Employee");

		// 从记录数
		ScrollableResults scroll = q.scroll();  // 得到滚动的结果集
		scroll.last();							//  滚动到最后一行
		int totalCount = scroll.getRowNumber() + 1;// 得到滚到的记录数，即总记录数

		// 设置分页参数
		q.setFirstResult(0);
		q.setMaxResults(3);

		// 查询
		System.out.println(q.list());
		System.out.println("总记录数：" + totalCount);

		session.getTransaction().commit();
		session.close();
	}
}










