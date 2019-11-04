package servlet;

import factory.BeanFactory;
import service.DinnerTableService;
import service.FoodService;
import service.FoodTypeService;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 项目中通用的Servlet，希望所有的servlet都继承此类
 */
public abstract class BaseServlet extends HttpServlet {

    // 创建Service
    protected DinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService");
    protected FoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService");
    protected FoodService foodService = BeanFactory.getInstance("foodService");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        // (保存跳转的资源)  方法返回值
        Object returnValue = null;

        // 获取操作类型;  【约定 > 俗成： 操作类型的值，必须对应servlet中的方法名称】
        String methodName = request.getParameter("method");

        try {
            // 1. 获取当前运行类的字节码
            Class clazz = this.getClass();

            // 2. 获取当前执行的方法的Method类型
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);

            // 3. 执行方法
            returnValue = method.invoke(this, request,response);

        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "/error/error.jsp";
        }

        // 跳转
        WebUtils.goTo(request, response, returnValue);
    }
}
