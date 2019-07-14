package session_learning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 保存会话数据到session域对象
 */
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建session对象
        HttpSession session = request.getSession();

        /**
         * 得到session编号
         */
        System.out.println("id="+session.getId());

        /**
         * 修改session的有效时间
         */
        //session.setMaxInactiveInterval(20);

        /**
         * 手动发送一个硬盘保存的cookie给浏览器
         */
        Cookie c = new Cookie("JSESSIONID",session.getId());
        c.setMaxAge(60*60);
        response.addCookie(c);

        //2.保存会话数据
        session.setAttribute("name", "rose");

        //3.取出数据
        String name = (String)session.getAttribute("name");
        System.out.println("name="+name);

        //4.手动销毁
        session.invalidate();

    }
}
