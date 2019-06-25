package d_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie cookie1 = new Cookie("name","eric");
        /**
         * 1）设置cookie的有效路径。默认情况：有效路径在当前web应用下。
         */
        //cookie1.setMaxAge(20); //20秒，从最后不调用cookie开始计算
        cookie1.setMaxAge(-1); //cookie保存在浏览器内存（会话cookie）

        //2.把cookie数据发送到浏览器
        response.addCookie(cookie1);

        //3.接收浏览器发送的cookie信息
        Cookie[] cookies = request.getCookies();
        //注意：判断null,否则空指针
        if(cookies!=null){
            //遍历
            for(Cookie c:cookies){
                String name = c.getName();
                String value = c.getValue();
                System.out.println(name+"="+value);
            }
        }else{
            System.out.println("没有接收cookie数据");
        }

        Cookie cookie = new Cookie("name","xxxx");
        cookie.setMaxAge(0);//删除同名的cookie
    }
}
