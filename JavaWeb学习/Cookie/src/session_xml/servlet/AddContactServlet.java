package session_xml.servlet;

import session_xml.dao.ContactDao;
import session_xml.dao.impl.ContactDaoImpl;
import session_xml.entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加联系人的逻辑
 *
 */

@WebServlet(value = "/AddContactServlet")
public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1.接收参数
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");

        //封装成Contact对象
        Contact contact = new Contact();
        contact.setName(name);
        contact.setGender(gender);
        contact.setAge(Integer.parseInt(age));
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setQq(qq);

        //2.调用dao类的添加联系人的方法
        ContactDao dao = new ContactDaoImpl();
        dao.addContact(contact);

        //3.跳转到查询联系人的页面
        response.sendRedirect(request.getContextPath()+"/ListContactServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
