package contactSys_jdbc.dao.impl;

import contactSys_jdbc.dao.ContactDao;
import contactSys_jdbc.entity.Contact;
import contactSys_jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactDaoImpl implements ContactDao {

    /**
     * 添加联系人
     */
    @Override
    public void addContact(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = JdbcUtil.getConnection();
            String sql = "INSERT INTO contact (id, name, gender, age, phone, email, qq) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            String id = UUID.randomUUID().toString().replace("-", "");

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getGender());
            preparedStatement.setInt(4, contact.getAge());
            preparedStatement.setString(5, contact.getPhone());
            preparedStatement.setString(6, contact.getEmail());
            preparedStatement.setString(7, contact.getQq());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(connection,preparedStatement);
        }

    }

    /**
     * 修改
     * @param contact
     */
    @Override
    public void updateContact(Contact contact) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = JdbcUtil.getConnection();
            String sql = "UPDATE contact SET name = ?, gender = ?, " +
                    "age = ?, phone = ?, email = ?, qq = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getGender());
            preparedStatement.setInt(3, contact.getAge());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setString(6, contact.getQq());
            preparedStatement.setString(7, contact.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(connection,preparedStatement);
        }
    }

    /**
     * 删除联系人
     * @param id
     */
    @Override
    public void deleteContact(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取连接
            connection = JdbcUtil.getConnection();

            String sql = "DELETE FROM contact WHERE id = ?";

            // 创建PreparedStatement
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            preparedStatement.setString(1, id);

            // 执行
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }

    }

    /**
     * 查询所有联系人
     * @return
     */
    @Override
    public List<Contact> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获取连接
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * FROM contact";

            // 创建PreparedStatement
            preparedStatement = connection.prepareStatement(sql);

            //执行
            resultSet = preparedStatement.executeQuery();
            List<Contact> list = new ArrayList<Contact>();
            while (resultSet.next()){
                Contact contact = new Contact();
                contact.setId(resultSet.getString("id"));
                contact.setName(resultSet.getString("name"));
                contact.setGender(resultSet.getString("gender"));
                contact.setAge(resultSet.getInt("age"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                contact.setQq(resultSet.getString("qq"));
                list.add(contact);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 根据Id查询联系人
     * @param id
     * @return
     */
    @Override
    public Contact findById(String id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获取连接
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * FROM contact where id = ?";

            // 创建PreparedStatement
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            //执行
            resultSet = preparedStatement.executeQuery();
            Contact contact = null;
            while (resultSet.next()){
                contact = new Contact();
                contact.setId(resultSet.getString("id"));
                contact.setName(resultSet.getString("name"));
                contact.setGender(resultSet.getString("gender"));
                contact.setAge(resultSet.getInt("age"));
                contact.setPhone(resultSet.getString("phone"));
                contact.setEmail(resultSet.getString("email"));
                contact.setQq(resultSet.getString("qq"));
            }
            return contact;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }


    /**
     * 根据姓名查询是否重复
     * @param name
     * @return
     */
    @Override
    public boolean checkContact(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "SELECT * FROM contact WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }

    }
}
