package c_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ForwardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 保存数据到request域对象
         */
        request.setAttribute("name", "rose");
        //转发
        /**
         * 注意：不能转发当前web应用以外的资源。
         */
		/*RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/GetDataServlet");
		rd.forward(request, response);*/
        this.getServletContext().getRequestDispatcher("/testParam.html").forward(request, response);
    }
}
