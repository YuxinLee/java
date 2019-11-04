package mystruts.servlet;

import javax.servlet.http.HttpServlet;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 创建Action对象，调用登陆方法
        LoginAction loginAction = new LoginAction();
        Object uri = loginAction.login(request, response);

        // 跳转
        if (uri instanceof String) {
            response.sendRedirect(request.getContextPath() + uri.toString());
        } else {
            ((RequestDispatcher)uri).forward(request, response);
        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doGet(request, response);
    }
}
