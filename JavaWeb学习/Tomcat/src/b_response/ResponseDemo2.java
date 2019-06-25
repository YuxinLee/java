package b_response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ResponseDemo2")
public class ResponseDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 请求重定向
        //        response.sendRedirect("/Tomcat_war_exploded/testParam.html");
        // 定时刷新
        response.setHeader("refresh","1");
        // 隔3秒之后跳转到testParam.html
        response.setHeader("refresh", "3;url=/Tomcat_war_exploded/testParam.html");
    }
}
