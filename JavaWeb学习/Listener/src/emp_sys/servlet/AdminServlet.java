package emp_sys.servlet;

import emp_sys.service.AdminService;
import emp_sys.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户管理servlet 1. 登陆 2. 退出
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {

    // Service实例
    private AdminService adminService = new AdminServiceImpl();

    // 跳转资源
    private String uri;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 区别不同的操作类型
        String method = request.getParameter("method");
        // 判断
        if ("login".equals(method)) {
            // 登陆操作, 调用登陆方法
            login(request, response);
        }

        else if ("out".equals(method)) {
            // 退出操作，调用推出方法
            out(request, response);
        }
    }

    // 1 登陆
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 获取参数
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        // 封装
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPwd(pwd);

        try {
            // 2. 调用service处理业务
            Admin loginInfo = adminService.findByNameAndPwd(admin);
            // 判断:
            if (loginInfo == null) {
                // 登陆失败
                uri = "/login.jsp";
            } else {
                // 登陆成功
                // 先，保存数据到session
                request.getSession().setAttribute("loginInfo", loginInfo);
                //【在线列表： 1. 先从servletContext中拿到在线列表集合;  (onLineUserList)
                //           2. 当前用户放入“在线列表集合中”】
                // 实现1：先得到servletContext对象
                ServletContext sc = getServletContext();
                // 实现2： 再获取在线列表集合
                List<Admin> onlineList = (List<Admin>) sc.getAttribute("onlineList");
                // 判断
                if (onlineList != null){
                    // 实现3: 添加当前登陆者
                    onlineList.add(loginInfo);
                    //sc.setAttribute("onlineList", onlineList);  // 对象引用传递,不需要写也可以
                }

                // 再，跳转到首页显示servlet(/index)
                uri = "/index";
            }
        } catch (Exception e) {
            // 测试
            e.printStackTrace();
            // 错误
            uri = "/error/error.jsp";
        }

        // 3. 跳转
        request.getRequestDispatcher(uri).forward(request, response);

    }

    // 2 退出
    private void out(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1. 获取session
        HttpSession session = request.getSession(false);
        //2.判断：
        if (session!=null) {
            // 从session中移除用户
            // session.removeAttribute("loginInfo");  //?
            // 销毁session
            session.invalidate();
        }
        //3. 跳转(登陆)
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }



}
