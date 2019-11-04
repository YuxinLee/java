package cn.itcast.d_myaop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 目标对象
 * @author Jie.Yuan
 *
 */
@Component   // 加入容器
public class UserDao implements IUserDao{

	// 重复执行代码形成的一个类
	@Resource
	private Aop aop;

	@Override
	public void save() {
		aop.begin();
		System.out.println("-----核心业务：保存！！！------");
		aop.commite();
	}



}