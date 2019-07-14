package XMLDemo1;

import java.util.List;

/**
 *
 * 该接口用于存放联系人相关操作的方法。
 */

public interface ContactOperator {

    //增加联系人
    public void addContact(Contact contact);

    //修改联系人
    public void updateContact(Contact contact);

    //根据ID删除联系人
    public void deleteContact(String id);

    //查询所有联系人
    public List<Contact> findAll();
}
