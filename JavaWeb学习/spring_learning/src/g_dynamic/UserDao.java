package cn.itcast.b_dynamic;

/**
 * 目标对象
 * @author Jie.Yuan
 *
 */
public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.out.println("-----已经保存数据！！！------");
	}

}