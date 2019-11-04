package cn.itcast.action;

import cn.itcast.entity.Dept;
import cn.itcast.service.DeptService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeptAction extends ActionSupport{

	// Service
	private DeptService deptService = new DeptService();

	// 默认处理方法
	public String execute() {
		// 主键查询(模拟数据)
		Dept dept = deptService.findById(12);
		// 保存
		ActionContext.getContext().getContextMap().put("dept", dept);
		return SUCCESS;
	}
}