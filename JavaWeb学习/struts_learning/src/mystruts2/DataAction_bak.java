package mystruts2;

import java.util.Map;

import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ���ݴ���
 * @author Jie.Yuan
 *
 */
public class DataAction_bak extends ActionSupport{
	


	@Override
	public String execute() throws Exception {
		
		// 1. �������ݷ�װ�� 2. ����Service����ҵ���߼����õ��������
		
		// 3. ���ݱ��浽����
		
		/*
		 * 
		// Struts�ж����ݲ�������ʽ1�� ֱ���õ�ServletApi, ִ�в���
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ServletContext application = ServletActionContext.getServletContext();
		// ����
		request.setAttribute("request_data", "request_data1");
		session.setAttribute("session_data", "session_data1");
		application.setAttribute("application_data", "application_data1");
		*/
		
		
		// ���Ƽ�������ķ�ʽʵ�ֶ����ݵĲ�����
		// Struts�ж����ݲ�������ʽ2�� ͨ��ActionContext�� 
		ActionContext ac = ActionContext.getContext();
		// �õ�Struts��HttpServletRequest��������˷�װ����װΪһ��map
		// �õ���ʾrequest�����map
	 	Map<String,Object> request =  ac.getContextMap(); 
	 	// �õ���ʾsession�����map
	 	Map<String, Object> session = ac.getSession();
	 	// �õ���ʾservletContext�����map
	 	Map<String, Object> application = ac.getApplication();
	 	
	 	// ����
	 	request.put("request_data", "request_data1_actionContext");
		session.put("session_data", "session_data1_actionContext");
		application.put("application_data", "application_data1_actionContext");
		
		return SUCCESS;
	}
}








