package cn.itcast.h_jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	// 容器对象
	ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/h_jdbc/bean.xml");

	@Test
	public void testApp() throws Exception {
		UserDao ud = (UserDao) ac.getBean("userDao");
//		ud.save();
		System.out.println(ud.findById(9));
		System.out.println(ud.getAll());
	}
}