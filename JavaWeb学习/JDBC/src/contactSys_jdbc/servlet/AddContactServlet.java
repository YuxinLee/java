package contactSys_jdbc.servlet;

import contactSys_jdbc.entity.Contact;
import contactSys_jdbc.exception.NameRepeatException;
import contactSys_jdbc.service.ContactService;
import contactSys_jdbc.service.impl.ContactServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * 添加联系人的逻辑
 *
 */
@WebServlet(value = "/AddContactServlet")
public class AddContactServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1. 接收参数
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");

        //2. 封装成Contact对象
        Contact contact = new Contact();
        contact.setName(name);
        contact.setGender(gender);
        contact.setAge(Integer.parseInt(age));
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setQq(qq);

        //3.调用dao类的添加联系人的方法
        ContactService contactService = new ContactServiceImpl();
        try {
            contactService.addContact(contact);
        } catch (NameRepeatException e) {
            //处理自定义业务异常
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/contactSys_jdbc/addContact.jsp").forward(request, response);
            return;
        }

        //4.跳转到查询联系人的页面
        response.sendRedirect(request.getContextPath()+"/ListContactServlet");

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
