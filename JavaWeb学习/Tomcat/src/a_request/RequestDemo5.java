package a_request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(value = "/RequestDemo5")
public class RequestDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*System.out.println("POST方式");
		InputStream in = request.getInputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		while(  (len=in.read(buf))!=-1 ){
			System.out.println(new String(buf,0,len));
		}*/

        /**
         * 统一方便地获取请求参数的方法
         */
		System.out.println("POST方式");
		//根据参数名得到参数值
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println(name+"="+password);

		System.out.println("=============================");
		Enumeration<String> enums = request.getParameterNames();
		while( enums.hasMoreElements() ){
			String paramName = enums.nextElement();
			String paramValue = request.getParameter(paramName);
			System.out.println(paramName+"="+paramValue);
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*	System.out.println("GET方式");
		//接收GET方式提交的参数
		String value = request.getQueryString();
		System.out.println(value);*/

        /**
         * 统一方便地获取请求参数的方法
         */

        Enumeration<String> enums = request.getParameterNames();
        while( enums.hasMoreElements() ){
            String paramName = enums.nextElement();

            //如果参数名是hobit，则调用getParameterValues
            if("hobit".equals(paramName)){
                /**
                 * getParameterValues(name): 根据参数名获取参数值（可以获取多个值的同名参数）
                 */
                System.out.println(paramName+":");
                String[] hobits = request.getParameterValues("hobit");
                for(String h: hobits){
				/*	if("GET".equals(request.getMethod())){
						h = new String(h.getBytes("iso-8859-1"),"utf-8");
					}*/
                    System.out.print(h+",");
                }
                System.out.println();
                //如果不是hobit，则调用getParameter
            }else{
                String paramValue = request.getParameter(paramName);
				/*
				if("GET".equals(request.getMethod())){
					paramValue = new String(paramValue.getBytes("iso-8859-1"),"utf-8");
				}*/

                System.out.println(paramName+"="+paramValue);
            }
        }
    }
}
