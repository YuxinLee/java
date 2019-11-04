package j_myaop1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	ApplicationContext ac = 
		new ClassPathXmlApplicationContext("cn/itcast/d_myaop1/bean.xml");

	@Test
	public void testApp() {
		IUserDao userDao = (IUserDao) ac.getBean("userDao_proxy");
		System.out.println(userDao.getClass());
		userDao.save();
	}
}
