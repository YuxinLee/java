package b_filter_login;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码处理统一写到这里(servlet中不需要再处理编码)
 *
 */
@WebFilter(value = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    // 过滤器业务处理方法：处理的公用的业务逻辑操作
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // 一、处理公用业务
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
