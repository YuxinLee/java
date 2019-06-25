package c_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 保存数据到request域对象
         */
        request.setAttribute("name", "rose");

        //重定向
        /**
         * 注意：可以跳转到web应用内，或其他web应用，甚至其他外部域名。
         */
        //response.sendRedirect("/day09/adv.html");
        response.sendRedirect("www.baidu.com");
    }
}
