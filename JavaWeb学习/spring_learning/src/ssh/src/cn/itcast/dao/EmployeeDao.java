package cn.itcast.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import cn.itcast.entity.Employee;

public class EmployeeDao {

	// 注入SessionFactory对象
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 查询
	 * @param emp
	 */
	public Employee findById(Serializable id) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
	}
}