package a_filter_learning;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 过滤器，测试
 */
public class HelloFilter implements javax.servlet.Filter {

    // 创建实例
    public HelloFilter(){
        System.out.println("1. 创建过滤器实例");
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        System.out.println("2. 执行过滤器初始化方法");

        // 获取过滤器在web.xml中配置的初始化参数
        String encoding = config.getInitParameter("encoding");
        System.out.println(encoding);

        // 获取过滤器在web.xml中配置的初始化参数的名称
        Enumeration<String> enums =  config.getInitParameterNames();
        while (enums.hasMoreElements()){
            // 获取所有参数名称：encoding、path
            String name = enums.nextElement();
            // 获取名称对应的值
            String value = config.getInitParameter(name);
            System.out.println(name + "\t" + value);
        }

    }

    // 过滤器业务处理方法： 在请求到达servlet之前先进入此方法处理公用的业务逻辑操作
    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        System.out.println("3. 执行过滤器业务处理方法");
        // 放行 (去到Servlet)
        // 如果有下一个过滤器，进入下一个过滤器，否则就执行访问servlet
        chain.doFilter(req, resp);

        System.out.println("5. Servlet处理完成，又回到过滤器");
    }

    public void destroy() {
        System.out.println("6. 销毁过滤器实例");
    }





}
