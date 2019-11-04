package mystruts2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * �Զ�������ת������
 * 
 * @author Jie.Yuan
 *
 */
public class MyConverter extends StrutsTypeConverter {

	// ������ Ҫ����Ŀ��Ҫ֧�ֵĸ�ʽ,��: yyyy-MM-dd/yyyyMMdd/yyyy��MM��dd��..

	// �ȶ�����Ŀ��֧�ֵ�ת���ĸ�ʽ
	DateFormat[] df = { new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyyMMdd"),
			new SimpleDateFormat("yyyy��MM��dd��") };

	/**
	 * ��Stringת��Ϊָ�������� ��String To Date��
	 * 
	 * @param context
	 *            ��ǰ�����Ļ���
	 * @param values
	 *            jsp���ύ���ַ�����ֵ
	 * @param toClass
	 *            Ҫת��Ϊ��Ŀ������
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		// �ж�: ���ݲ���Ϊ��
		if (values == null || values.length == 0) {
			return null;
		}
		// �ж����ͱ���ΪDate
		if (Date.class != toClass) {
			return null;
		}
		
		// ������ת��ʧ�ܼ�����һ����ʽ��ת���� ת���ɹ���ֱ�ӷ���
		for (int i=0; i<df.length; i++) {
			try {
				return df[i].parse(values[0]);
			} catch (ParseException e) {
				continue;
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return null;
	}

}
