package fileupload;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �ļ�����
 * 1. ��ʾ����Ҫ�����ļ����б�
 * 2. �ļ�����
 * @author Jie.Yuan
 *
 */
public class DownAction extends ActionSupport {
	
	
	/*************1. ��ʾ����Ҫ�����ļ����б�*********************/
	public String list() throws Exception {
		
		//�õ�uploadĿ¼·��
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		// Ŀ¼����
		File file  = new File(path);
		// �õ�����Ҫ���ص��ļ����ļ���
		String[] fileNames =  file.list();
		// ����
		ActionContext ac = ActionContext.getContext();
		// �õ�����request��map (�ڶ��ַ�ʽ)
		Map<String,Object> request= (Map<String, Object>) ac.get("request");
		request.put("fileNames", fileNames);
		return "list";
	}
	
	
	/*************2. �ļ�����*********************/
	
	// 1. ��ȡҪ���ص��ļ����ļ���
	private String fileName;
	public void setFileName(String fileName) {
		// ������Ĳ���������(get�ύ)
		try {
			fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// �Ѵ���õ��ļ�������ֵ
		this.fileName = fileName;
	}
	
	//2. �����ύ��ҵ�񷽷� (��struts.xml�����÷���stream)
	public String down() throws Exception {
		return "download";
	}
	
	// 3. �����ļ����ķ���
	public InputStream getAttrInputStream(){
		return ServletActionContext.getServletContext().getResourceAsStream("/upload/" + fileName);
	}
	
	// 4. ������ʾ���ļ������������ʾ���ļ�����
	public String getDownFileName() {
		// ��Ҫ�������ı���
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return fileName;
	}

	
}










