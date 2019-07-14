package ContactSys.service;



import ContactSys.entity.Contact;
import ContactSys.exception.NameRepeatException;

import java.util.List;

public interface ContactService {

	//添加联系人
	public void addContact(Contact contact)throws NameRepeatException;

	//修改联系人
	public void updateContact(Contact contact);

	//删除联系人
	public void deleteContact(String id);

	//查询所有联系人
	public List<Contact> findAll();

	//根据编号查询联系人
	public Contact findById(String id);
}
 