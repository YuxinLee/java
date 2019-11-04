package a_life;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听session对象属性的变化
 */
public class MySessionAttrListener implements HttpSessionAttributeListener {

    // 属性添加
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 先获取session对象
        HttpSession session = se.getSession();
        // 获取添加的属性
        Object obj = session.getAttribute("userName");
        // 测试
        System.out.println("添加的属性：" + obj);
    }

    // 属性移除
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("属性移除");
    }

    // 属性被替换
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 获取sesison对象
        HttpSession session = se.getSession();

        // 获取替换前的值
        Object old = se.getValue();
        System.out.println("原来的值：" + old);

        // 获取新值
        Object obj_new = session.getAttribute("userName");
        System.out.println("新值：" + obj_new);

    }

}
