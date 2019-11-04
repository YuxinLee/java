package mystruts2;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ���ݴ���, ��ʽ3�� ʵ�ֽӿڵķ���
 * @author Jie.Yuan
 *
 */
public class DataAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware{
	
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	
	// struts����ʱ�򣬻�Ѵ���request��map����ע��
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	// ע��session
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	// ע��application
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}


	@Override
	public String execute() throws Exception {
		
	 	// ����
	 	request.put("request_data", "request_data1_actionAware");
		session.put("session_data", "session_data1_actionAware");
		application.put("application_data", "application_data1_actionAware");
//		
		return SUCCESS;
	}

}








