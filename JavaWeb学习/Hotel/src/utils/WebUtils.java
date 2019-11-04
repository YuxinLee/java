package utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 跳转的通用方法
     */
    public static void goTo(HttpServletRequest request, HttpServletResponse response, Object url)
                    throws ServletException, IOException {
        if (url instanceof RequestDispatcher) {
            ((RequestDispatcher) url).forward(request, response);
        } else if (url instanceof String) {
            response.sendRedirect(request.getContextPath() + url);
        }

    }
}
