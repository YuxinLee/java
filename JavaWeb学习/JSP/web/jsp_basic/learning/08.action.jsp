<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>动作标签</title>  
  </head>
  
  <body>
    <%--转发 --%>
    <%
    	//request.getRequestDispatcher("/09.action2.jsp?name=eric").forward(request,response);
     %>
    <%-- 参数 --%>
    <%--
    <jsp:forward page="/09.action2.jsp">
    	<jsp:param value="jacky" name="name"/>
    	<jsp:param value="123456" name="password"/>
    </jsp:forward>
      --%>
      
      <%--包含 --%>
      <%--
   <jsp:include page="/common/header.jsp">
   		<jsp:param value="lucy" name="name"/>
   	</jsp:include>
   	 --%>
   	 <%@include file="../common/header.jsp" %>
      主页的内容
     
  </body>
</html>
