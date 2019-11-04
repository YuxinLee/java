package cn.itcast.interceptor;

import org.hibernate.Session;

import cn.itcast.utils.HibernateUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Session管理拦截器：
 * 	  当访问action的时候，创建session；
 * 	 action ---> service  --> dao 【获取的是这里创建的session】
 * @author Jie.Yuan
 *
 */
public class SessionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		try {
			// 1. 先创建Session
			Session session = HibernateUtils.getSession();
			// 2. 开启事务
			session.beginTransaction();

			// 3. 执行Action
			String result = invocation.invoke();

			// 4. 提交事务
			session.getTransaction().commit();  // 不需要关闭session

			// 返回结果视图
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}