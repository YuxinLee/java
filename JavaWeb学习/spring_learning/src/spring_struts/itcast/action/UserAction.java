package cn.itcast.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.service.UserService;

public class UserAction extends ActionSupport {

	// springIOC容器注入
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public String execute() {
		userService.save();
		return SUCCESS;
	}
}







