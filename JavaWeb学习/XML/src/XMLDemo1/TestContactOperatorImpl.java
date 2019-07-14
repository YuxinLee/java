package XMLDemo1;


import org.junit.Test;

import java.util.List;

/**
 * 联系人操作实现类的测试类
 *
 *
 */
public class TestContactOperatorImpl {
    ContactOperator operator = new ContactOperatorImpl();

    //添加测试
    @Test
    public void testAddContact(){
        Contact contact = new Contact();
        contact.setId("4");
        contact.setName("rose");
        contact.setGender("男");
        contact.setAge(20);
        contact.setPhone("134222233333");
        contact.setEmail("eric@qq.com");
        contact.setQq("33334444");
        operator.addContact(contact);
    }

    //更新测试
    @Test
    public void testUpdateContact() {
        Contact contact = new Contact();
        contact.setId("3"); // 修改的ID
        contact.setName("zhangsan");
        contact.setGender("女");
        contact.setAge(30);
        contact.setPhone("135222233333");
        contact.setEmail("zhangsan@qq.com");
        contact.setQq("33334444");
        operator.updateContact(contact);

    }

    //删除测试
    @Test
    public void testDeleteContact() {
        operator.deleteContact("4");
    }

    //查询测试
    @Test
    public void testFindAll() {
        List<Contact> list = operator.findAll();
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }
}
