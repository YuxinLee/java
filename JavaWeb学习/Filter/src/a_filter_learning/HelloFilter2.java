package a_filter_learning;

import javax.servlet.*;
import java.io.IOException;

public class HelloFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("第二个过滤器");
        // 放心
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("第二个过滤器执行结束");
    }

    @Override
    public void destroy() {

    }
}
