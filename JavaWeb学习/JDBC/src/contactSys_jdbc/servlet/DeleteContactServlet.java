package contactSys_jdbc.servlet;

import contactSys_jdbc.service.ContactService;
import contactSys_jdbc.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除联系人的逻辑
 *
 */
@WebServlet(value = "/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String id = request.getParameter("id");

        //2.调用service删除联系人的方法
        ContactService service = new ContactServiceImpl();
        service.deleteContact(id);

        //3.跳转到查询联系人的页面
        response.sendRedirect(request.getContextPath()+"/ListContactServlet");
    }
}
