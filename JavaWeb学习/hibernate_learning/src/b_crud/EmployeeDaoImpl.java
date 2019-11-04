package b_crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itcast.a_hello.Employee;
import cn.itcast.utils.HibernateUtils;

public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public Employee findById(Serializable id) {
		Session session = null;
		Transaction tx = null;
		try {
			// 获取Session
			session = HibernateUtils.getSession();
			// 开启事务
			tx = session.beginTransaction();
			// 主键查询
			return (Employee) session.get(Employee.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	@Override
	public List<Employee> getAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// HQL查询
			Query q = session.createQuery("from Employee");
			return q.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	@Override
	public List<Employee> getAll(String employeeName) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Query q =session.createQuery("from Employee where empName=?");
			// 注意：参数索引从0开始
			q.setParameter(0, employeeName);
			// 执行查询
			return q.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	@Override
	public List<Employee> getAll(int index, int count) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery("from Employee");
			// 设置分页参数
			q.setFirstResult(index);  // 查询的其实行
			q.setMaxResults(count);	  // 查询返回的行数

			List<Employee> list = q.list();
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}

	@Override
	public void save(Employee emp) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// 执行保存操作
			session.save(emp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}

	}

	@Override
	public void update(Employee emp) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			session.update(emp);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}

	}

	@Override
	public void delete(Serializable id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getSession();
			tx = session.beginTransaction();
			// 先根据id查询对象，再判断删除
			Object obj = session.get(Employee.class, id);
			if (obj != null) {
				session.delete(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			tx.commit();
			session.close();
		}
	}
}