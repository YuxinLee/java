package emp_sys.listener;

import emp_sys.entity.Admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化在线列表集合监听器
 */
public class OnlineAdminListener {

    //1. ServletContext对象创建
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 创建集合：存放在线用户
        // 每次当用户登陆后，就往这个集合中添加添加当前登陆者
        List<Admin> onlineList = new ArrayList<Admin>();
        // 放入ServletContext中
        sce.getServletContext().setAttribute("onlineList", onlineList);
    }

    //2. ServletContext对象销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 获取ServletContext
        ServletContext sc = sce.getServletContext();
        // 获取在线列表
        Object obj = sc.getAttribute("onlineList");
        // 移除在线列表集合
        if (obj != null) {
            sc.removeAttribute("onlineList");
        }
    }
}
