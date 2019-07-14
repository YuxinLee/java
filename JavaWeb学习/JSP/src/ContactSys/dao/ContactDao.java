package ContactSys.dao;



import ContactSys.entity.Contact;

import java.util.List;

/**
 * 联系人的DAO接口
 * @author APPle
 *
 */
public interface ContactDao {

	//添加联系人
	public void addContact(Contact contact);

	//修改联系人
	public void updateContact(Contact contact);

	//删除联系人
	public void deleteContact(String id);

	//查询所有联系人
	public List<Contact> findAll();

	//根据编号查询联系人
	public Contact findById(String id);

	//根据姓名查询是否重复
	public boolean checkContact(String name);
}
