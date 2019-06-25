package c_servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.得到ServletContext对象
        ServletContext context = this.getServletContext();
        //2.得到web应用路径
        String contextPath = context.getContextPath();
        System.out.println(contextPath);
        System.out.println("参数"+context.getInitParameter("AAA"));

        Enumeration<String> enums = context.getInitParameterNames();
        while(enums.hasMoreElements()){
            String paramName = enums.nextElement();
            String paramValue  =context.getInitParameter(paramName);
            System.out.println(paramName+"="+paramValue);
        }

        //尝试得到ConfigDemo中的servlet参数,不能获取
        String path = this.getServletConfig().getInitParameter("path");
        System.out.println("path="+path);

        //把数据保存到域对象中
        context.setAttribute("name", "eric");
        context.setAttribute("student", new Student("jacky",20));
        System.out.println("保存成功");

        //从域对象中取出数据
        Student student = (Student)context.getAttribute("student");
        System.out.println(student);


        /**
         * 案例：应用到请求重定向
         */
        response.sendRedirect(contextPath+"/testMethod.html");

    }
}

class Student{
    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [age=" + age + ", name=" + name + "]";
    }

}
