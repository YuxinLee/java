package a_life;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session对象创建、销毁
 */
public class MySessionListener implements HttpSessionListener {

    // 创建
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("MySessionListener.sessionCreated()");
    }

    // 销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("MySessionListener.sessionDestroyed()");
    }
}
