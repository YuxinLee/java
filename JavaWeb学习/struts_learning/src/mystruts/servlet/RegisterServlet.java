package mystruts.servlet;

public class RegisterServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterAction registerAction = new RegisterAction();
        Object uri = registerAction.register(request, response);

        // 配置文件---》jsp

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
