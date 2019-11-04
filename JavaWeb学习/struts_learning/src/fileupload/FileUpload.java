package fileupload;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileUpload extends ActionSupport {

	// ��Ӧ����<input type="file" name="file1">
	private File file1; 
	// �ļ���
	private String file1FileName;
	// �ļ�������(MIME)
	private String file1ContentType;
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	
	
	@Override
	public String execute() throws Exception {
		/******�õ��ϴ����ļ������д���******/
		// ���ļ��ϴ���uploadĿ¼
		
		// ��ȡ�ϴ���Ŀ¼·��
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		// ����Ŀ���ļ�����
		File destFile = new File(path,file1FileName);
		// ���ϴ����ļ���������Ŀ���ļ���
		FileUtils.copyFile(file1, destFile);
		
		return SUCCESS;
	}
}
