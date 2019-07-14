package contactSys_jdbc.service.impl;

import contactSys_jdbc.dao.ContactDao;
import contactSys_jdbc.dao.impl.ContactDaoImpl;
import contactSys_jdbc.entity.Contact;
import contactSys_jdbc.exception.NameRepeatException;
import contactSys_jdbc.service.ContactService;

import java.util.List;

/**
 * 业务逻辑层
 * 处理项目中出现的业务逻辑
 *
 */
public class ContactServiceImpl implements ContactService {

    ContactDao contactDao = new ContactDaoImpl();

    @Override
    public void addContact(Contact contact) throws NameRepeatException {
        //执行业务逻辑判断
        if (contactDao.checkContact(contact.getName())){
            //重复
            /**
             * 注意： 如果业务层方法出现任何业务异常，则返回标记（自定义异常）到servlet
             */
            throw new NameRepeatException("姓名重复，不可使用");
        }
        //如果不重复，才执行添加方法
        contactDao.addContact(contact);

    }

    @Override
    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);

    }

    @Override
    public void deleteContact(String id) {
        contactDao.deleteContact(id);

    }

    @Override
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    @Override
    public Contact findById(String id) {
        return contactDao.findById(id);
    }
}
