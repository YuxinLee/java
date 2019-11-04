package emp_sys.servlet;

import emp_sys.entity.Employee;
import emp_sys.service.EmployeeService;
import emp_sys.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/IndexServlet")
public class IndexServlet extends HttpServlet {

    // Service实例
    private EmployeeService employeeService = new EmployeeServiceImpl();

    // 跳转资源
    private String uri;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 调用service查询所有
            List<Employee> list = employeeService.getAll();
            request.setAttribute("listEmp", list);
            // 进入首页jsp
            uri = "/list.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            uri = "/error/error.jsp";
        }

        // 转发
        request.getRequestDispatcher(uri).forward(request, response);
    }
}
