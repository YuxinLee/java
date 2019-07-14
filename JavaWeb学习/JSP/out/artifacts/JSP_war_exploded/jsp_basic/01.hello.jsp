<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: leeyuxin
  Date: 19-7-1
  Time: 下午9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>第一个jsp页面</title>
</head>
<body>
    <%
        //写java代码
        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        String curDate = sdf.format(new Date());
        //输出内容到浏览器
        //response.getWriter().write("");
        out.write("当前时间为："+curDate);
    %>

</body>
</html>
