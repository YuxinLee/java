package b_response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/ResponseDemo3")
public class ResponseDemo3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 服务器发送给浏览器的数据类型和内容编码
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("<html><head><title>this is tilte</title></head><body>中国</body></html>");
    }
}
