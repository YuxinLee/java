package d_page.servlet;

import d_page.entity.Employee;
import d_page.service.EmployeeService;
import d_page.service.impl.EmployeeSerivceImpl;
import d_page.utils.PageBean;

import java.io.IOException;

public class IndexServlet extends javax.servlet.http.HttpServlet {

    // 创建Service实例
    private EmployeeService employeeService = new EmployeeSerivceImpl();

    // 跳转资源
    private String uri;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            //1. 获取“当前页”参数；  (第一次访问当前页为null)
            String currPage = request.getParameter("currentPage");

            // 判断
            if (currPage == null || "".equals(currPage.trim())){
                currPage = "1";  	// 第一次访问，设置当前页为1;
            }

            // 转换
            int currentPage = Integer.parseInt(currPage);

            //2. 创建PageBean对象，设置当前页参数； 传入service方法参数
            PageBean<Employee> pageBean = new PageBean<Employee>();
            pageBean.setCurrentPage(currentPage);

            //3. 调用service
            employeeService.getAll(pageBean);    // 【pageBean已经被dao填充了数据】

            //4. 保存pageBean对象，到request域中
            request.setAttribute("pageBean", pageBean);

            //5. 跳转
            uri = "/WEB-INF/list.jsp";

        } catch (Exception e) {
            e.printStackTrace();  // 测试使用
            // 出现错误，跳转到错误页面；给用户友好提示
            uri = "/error/error.jsp";

        }

        request.getRequestDispatcher(uri).forward(request, response);

    }
}
