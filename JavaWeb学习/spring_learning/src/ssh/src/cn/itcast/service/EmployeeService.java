package cn.itcast.service;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import cn.itcast.dao.EmployeeDao;
import cn.itcast.entity.Employee;

public class EmployeeService {

	// IOC注入
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 查询
	 * @param emp
	 */
	public Employee findById(Serializable id) {
		Employee emp = employeeDao.findById(id);
		return emp;
	}
}