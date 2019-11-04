package a_life;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听ServletContext对象创建或销毁
 */
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyServletContextListener.contextDestroyed()");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("1..........MyServletContextListener.contextInitialized()");
    }

}
