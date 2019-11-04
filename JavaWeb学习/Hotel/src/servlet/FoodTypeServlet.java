package servlet;

import entity.FoodType;
import factory.BeanFactory;
import service.FoodTypeService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 菜系管理Servlet开发
 *
 * a. 添加菜系
 * b. 菜系列表展示
 * c. 进入更新页面
 * d. 删除
 * e. 更新
 *
 */

@WebServlet(value = "/foodType")
public class FoodTypeServlet extends BaseServlet {

    /**
     * 第一种写法

    // 调用的菜系Service
    private FoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService");

    //跳转资源
    private Object url;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取操作的类型
        String method = request.getParameter("method");

        //判断
        if ("addFoodType".equals(method)) {
            // 添加
            addFoodType(request, response);
        }

         else if ("list".equals(method)) {
             list(request, response);
        }

         else if ("viewUpdate".equals(method)) {
             viewUpdate(request, response);
        }

         else if ("delete".equals(method)) {
             delete(request, response);
        }

         else if ("update".equals(method)) {
             update(request, response);
        }
    }

    //a. 添加菜系
    public void addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //1.获取请求数据封装
            String foodTypeName = request.getParameter("foodTypeName");
            FoodType foodType =new FoodType();
            foodType.setTypeName(foodTypeName);

            //2. 调用service处理业务逻辑
            foodTypeService.save(foodType);

            //3. 跳转
            //获取到RequestDispatcher对象，但是此时并未转发，因还要有.forward(request,response)
            url = request.getRequestDispatcher("/foodType?method=list");
        } catch (Exception e) {
            e.printStackTrace();
            url = "/sys/error/error.jsp";
        }

        goTo(request, response, url);
    }

    //b. 菜系列表展示
    public void list(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // 调用Service查询所有的类别
            List<FoodType> list = foodTypeService.getAll();

            //保存
            request.setAttribute("listFoodType", list);

            //跳转的菜系列表页面
            url = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            url = "/sys/error/error.jsp";
        }

        //跳转
        goTo(request, response, url);
    }

    //c. 进入更新页面
    public void viewUpdate(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // 1. 获取请求id
            String id = request.getParameter("id");

            // 2. 根据id查询对象
            FoodType foodType = foodTypeService.findById(Integer.parseInt(id));

            // 3. 保存
            request.setAttribute("foodType", foodType);

            // 4. 跳转
            url = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            url = "/sys/error/error.jsp";
        }

        goTo(request, response, url);
    }

    //d. 删除
    public void delete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            // 1. 获取请求id
            String id = request.getParameter("id");

            // 2. 调用Service
            foodTypeService.delete(Integer.parseInt(id));

            // 3. 跳转
            url = "/sys/type/foodType?method=list";
        } catch (Exception e) {
            e.printStackTrace();
            url = "/sys/error/error.jsp";
        }

        goTo(request, response, url);
    }

    // e. 更新
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //1. 获取请求数据封装
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("foodTypeName");
            FoodType foodType = new FoodType();
            foodType.setId(id);
            foodType.setTypeName(name);

            //2. 调用Service更新
            foodTypeService.update(foodType);

            //3. 跳转
            //list(request,response);
            url = "/sys/type/foodType?method=list";

        } catch (Exception e) {
            e.printStackTrace();
            url = "/sys/error/error.jsp";
        }

        // 跳转
        goTo(request, response, url);
    }


     //跳转的通用方法
    private void goTo(HttpServletRequest request, HttpServletResponse response, Object url) throws ServletException, IOException {
        if (url instanceof RequestDispatcher) {
            ((RequestDispatcher) url).forward(request, response); //转发

        } else if (url instanceof String) {
            response.sendRedirect(request.getContextPath() + url); //重定向
        }
    }

    */

    //第二种写法
    // a. 添加菜系
    public Object addFoodType(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Object url = null;

        // 1. 获取请求数据封装
        String foodTypeName = request.getParameter("foodTypeName");
        FoodType ft = new FoodType();
        ft.setTypeName(foodTypeName);

        // 2. 调用service处理业务逻辑
        foodTypeService.save(ft);

        // 3. 跳转
        url = request.getRequestDispatcher("/foodType?method=list");

        return url;

    }

    // b. 菜系列表展示
    public Object list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object url = null;

        // 调用Service查询所有的类别
        List<FoodType> list = foodTypeService.getAll();

        // 保存
        request.setAttribute("listFoodType", list);

        // 跳转的菜系列表页面
        url = request.getRequestDispatcher("/sys/type/foodtype_list.jsp");

        return url;

    }

    // c. 进入更新页面
    public Object viewUpdate(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        Object url = null;

        // 1. 获取请求id
        String id = request.getParameter("id");

        // 2. 根据id查询对象
        FoodType ft = foodTypeService.findById(Integer.parseInt(id));

        // 3. 保存
        request.setAttribute("foodType", ft);

        // 4. 跳转
        url = request.getRequestDispatcher("/sys/type/foodtype_update.jsp");

        return url;
    }

    // d. 删除
    public Object delete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Object url = null;

        // 1. 获取请求id
        String id = request.getParameter("id");

        // 2. 调用Service
        foodTypeService.delete(Integer.parseInt(id));

        // 3. 跳转
        url = "/foodType?method=list";

        return url;
    }

    // e. 更新
    public Object update(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Object url = null;

        // 1. 获取请求数据封装
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("foodTypeName");
        FoodType foodType = new FoodType();
        foodType.setId(id);
        foodType.setTypeName(name);

        // 2. 调用Service更新
        foodTypeService.update(foodType);

        // 3. 跳转
        // list(request,response);
        url = "/foodType?method=list";
        return url;
    }
}

