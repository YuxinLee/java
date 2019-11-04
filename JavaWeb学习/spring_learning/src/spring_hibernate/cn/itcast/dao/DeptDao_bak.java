package cn.itcast.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import cn.itcast.entity.Dept;

// 数据访问层
public class DeptDao_bak {

	private static SessionFactory sessionFactory;
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	// 保存一个记录
	public void save(Dept dept) {
		// 创建配置管理类对象、创建SessionFactory、创建Session,  执行操作， 关闭
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 保存
		session.save(dept);
		session.getTransaction().commit();
		session.close();
	}
}