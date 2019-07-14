package contactSys_jdbc.servlet;

import contactSys_jdbc.entity.Contact;
import contactSys_jdbc.service.ContactService;
import contactSys_jdbc.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 显示所有联系人的逻辑
 *
 */
@WebServlet(value = "/ListContactServlet")
public class ListContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 从数据库中读取联系人数据
        ContactService contactService = new ContactServiceImpl();
        List<Contact> list = contactService.findAll();

        //2.把结果保存到域对象中
        request.setAttribute("contacts", list);

        //3.跳转到jsp页面(转发)
        request.getRequestDispatcher("/contactSys_jdbc/listContact.jsp").forward(request, response);
    }
}
