<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>EL语法</title>  
  </head>
  
  <body>
    <%
	 String name = "rose";  
	 //放入域中
	 //pageContext.setAttribute("name",name);
	 pageContext.setAttribute("name",name,PageContext.REQUEST_SCOPE); 
	  %>
	  <%=name %>
	  <br/>
	  <%--
	  1)从四个域自动搜索
	   --%>
	  EL表达式： ${name }
	  <%--
	  	${name } 等价于
	  	    <%=pageContext.findAttribute("name")%>
	   --%>
	   <%--
	    2） 从指定的域中获取数据
	    --%><br/>
	    EL表达式： ${pageScope.name }
	    <%--
	    	${pageScope.name } 等价于
	    	 <%= pageContext.getAttribute("name",PageContext.PAGE_SCOPE)%>
	    	
	     --%>
  </body>
</html>
