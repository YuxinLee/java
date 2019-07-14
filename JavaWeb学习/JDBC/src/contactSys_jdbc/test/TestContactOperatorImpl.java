package contactSys_jdbc.test;

import contactSys_jdbc.dao.ContactDao;
import contactSys_jdbc.dao.impl.ContactDaoImpl;
import contactSys_jdbc.entity.Contact;
import contactSys_jdbc.exception.NameRepeatException;
import contactSys_jdbc.service.ContactService;
import contactSys_jdbc.service.impl.ContactServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 联系人操作实现类的测试类
 *
 */
public class TestContactOperatorImpl {
    ContactDao contactDao = null;

    /**
     * 初始化对象实例
     */
    @Before
    public void init(){
        contactDao = new ContactDaoImpl();
    }

    //添加联系人测试
    @Test
    public void testAddContact(){

        Contact contact = new Contact();
        contact.setName("张三");
        contact.setGender("男");
        contact.setAge(20);
        contact.setPhone("13233334444");
        contact.setEmail("135@qq.com");
        contact.setQq("46789034");
        contactDao.addContact(contact);
    }

    //更新联系人测试
    @Test
    public void testUpdateContact(){
        Contact contact = new Contact();
        contact.setId("96ca01fcdfd74bd083b9e21b4c71a63a");
        contact.setName("李四");
        contact.setGender("女");
        contact.setAge(30);
        contact.setPhone("135222233333");
        contact.setEmail("zhangsan@qq.com");
        contact.setQq("33334444");
        contactDao.updateContact(contact);
    }

    //删除联系人测试
    @Test
    public void testDeleteContact(){
        contactDao.deleteContact("0119fc8af13a4545bb24f59afc616f5e");
    }

    //查询所有联系人
    @Test
    public void testFindAll(){
        List<Contact> list = contactDao.findAll();
        for (Contact contact : list) {
            System.out.println(contact);
        }

    }

    //根据ID查询联系人
    @Test
    public void testFindById(){
        Contact contact = contactDao.findById("0119fc8af13a4545bb24f59afc616f5e");
        System.out.println(contact);
    }

    //判断联系人的名字是否重复
    @Test
    public void testCheckContact(){
        System.out.println(contactDao.checkContact("张三"));
    }
}
