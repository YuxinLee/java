package cn.itcast.a_static;

/**
 * 代理对象(静态代理)
 * 	   代理对象，要实现与目标对象一样的接口
 * @author Jie.Yuan
 *
 */
public class UserDaoProxy implements IUserDao{

	// 接收保存目标对象
	private IUserDao target;
	public UserDaoProxy(IUserDao target) {
		this.target = target;
	}

	@Override
	public void save() {
		System.out.println("开始事务...");

		target.save(); 			// 执行目标对象的方法

		System.out.println("提交事务...");
	}



}