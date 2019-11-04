package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * �Զ���������
 * @author Jie.Yuan
 *
 */
public class HelloInterceptor implements Interceptor{
	
	// ����ʱ��ִ��
	public HelloInterceptor(){
		System.out.println("����������������");
	}

	// ����ʱ��ִ��
	@Override
	public void init() {
		System.out.println("ִ�����������ĳ�ʼ������");
	}

	// ������ҵ������ ���ڷ���actionʱ��ִ�У� ��execute֮ǰִ�У���
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("2. ִ��Action֮ǰ");
		
		// ������һ����������ִ��Action  (�൱��chain.doFilter(..)
		// ��ȡ���ǣ� execute�����ķ���ֵ
		String resultFlag = invocation.invoke();
		
		System.out.println("4. ��������ҵ����-����" + resultFlag);
		
		return resultFlag;
	}
	

	@Override
	public void destroy() {
		System.out.println("����....");
	}


}
