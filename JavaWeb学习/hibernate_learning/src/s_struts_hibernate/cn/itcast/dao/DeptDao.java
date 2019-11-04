package cn.itcast.dao;

import cn.itcast.entity.Dept;
import cn.itcast.utils.HibernateUtils;

public class DeptDao {

	/**
	 * 主键查询
	 */
	public Dept findById(int id){
		// 获取session, 执行操作
		return (Dept) HibernateUtils.getSession().get(Dept.class, id);
	}
}