package servlet;

import entity.DinnerTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/IndexServlet")
public class IndexServlet extends BaseServlet {

    // 1. 前台首页：显示所有未预定的餐桌
    public Object listTable(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {

        // 保存跳转资源(转发/重定向)
        Object url = null;

        // 查询所有未预定餐桌
        List<DinnerTable> list = dinnerTableService.findNoUseTable();

        //保存到request
        request.setAttribute("listDinnerTable", list);

        // 跳转到首页显示
        url = request.getRequestDispatcher("/app/index.jsp");

        return url;
    }

}
