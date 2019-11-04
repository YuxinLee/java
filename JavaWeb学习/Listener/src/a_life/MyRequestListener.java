package a_life;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *  监听request对象的创建或销毁
 *
 */
public class MyRequestListener implements ServletRequestListener {

    // 对象销毁
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // 获取request中存放的数据
        Object obj = sre.getServletRequest().getAttribute("cn");
        System.out.println(obj);
        System.out.println("MyRequestListener.requestDestroyed()");
    }

    // 对象创建
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("MyRequestListener.requestInitialized()");
    }

}
