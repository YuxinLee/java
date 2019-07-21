package register_jdbc.servlet;

import register_jdbc.entity.Admin;
import register_jdbc.exception.UserExistsException;
import register_jdbc.service.AdminService;
import register_jdbc.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/AdminServlet")
public class AdminServlet extends HttpServlet {

    // 调用的service
    private AdminService adminService = new AdminServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取操作类型
        String method = request.getParameter("method");
        if ("register".equals(method)) {
            register(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 注册处理方法
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void register(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        //1. 获取请求参数
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");

        // 封装
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPwd(pwd);

        // 使用BeanUtils组件处理请求数据的封装
        //Admin admin = WebUtils.copyToBean(request, Admin.class);

        //2. 调用Service处理注册的业务逻辑
        try {
            adminService.register(admin);

            // 注册成功，跳转到首页
            request.getRequestDispatcher("/register_jdbc/index.jsp").forward(request, response);
        } catch (UserExistsException e) {

            // 用户名存在，注册失败(跳转到注册页面)
            request.setAttribute("message", "用户名已经存在");

            // 转发
            request.getRequestDispatcher("/register_jdbc/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 其他错误, 跳转到错误页面
            response.sendRedirect(request.getContextPath() + "/register_jdbc/error/error.jsp");
        }
    }
}
