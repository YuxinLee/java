<%@ page language="java" import="java.util.*" pageEncoding="utf-8" 
isErrorPage="true" 
session="true"
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>pageContext对象</title>  
  </head>
  
  <body>
    <%
    	//1）可以获取其他8个内置对象
    	//response.getWriter().write("是否相等？"+(out==pageContext.getOut()) +"<br/>");
    	//response.getWriter().write("是否相等？"+(session==pageContext.getSession()));
     %>
     
     
     <%--
     	2)pageContext作为域对象使用
     			2.1 可以往不同域对象中存取数据
      --%>
      <%
      	//保存数据。默认情况下，保存在page域中
      	pageContext.setAttribute("message","page's message");
      	pageContext.setAttribute("message","request's messsage",PageContext.REQUEST_SCOPE);//保存到request域中
      	pageContext.setAttribute("message","session's messsage",PageContext.SESSION_SCOPE);//保存到sessio域中
      	pageContext.setAttribute("message","application's messsage",PageContext.APPLICATION_SCOPE);//保存到context域中
      	//request.setAttribute("message","request's message"); 等价于上面的代码
 
       %>
       
       <%
       	//获取数据
       	//String message = (String)pageContext.getAttribute("message");
       	//out.write(message);
       
        %>
        <%--从request域中取出数据 --%>
        <%--
     	      原则： 
           	1）在哪个域中保存数据，就必须从哪个域取出数据！！！
         --%>
        <%=pageContext.getAttribute("message",PageContext.PAGE_SCOPE) %><br/>
        <%=pageContext.getAttribute("message",PageContext.REQUEST_SCOPE) %><br/>
        <%=pageContext.getAttribute("message",PageContext.SESSION_SCOPE) %><br/>
        <%=pageContext.getAttribute("message",PageContext.APPLICATION_SCOPE) %><br/>
        <hr/>
        <%--
        	findAttribute(): 在四个域自动搜索数据
        		顺序： page域 -> request域  -> session域 -> context域
        		
         --%>
         <%=pageContext.findAttribute("message") %>
         <% //request.getAttribute("message") %><br/>
      
      
      <%
      	//转发
          request.getRequestDispatcher("/03.pageContext2.jsp").forward(request,response);
      	//重定向
      //	response.sendRedirect(request.getContextPath()+"/03.pageContext2.jsp");

    <%=pageContext.getAttribute("message",PageContext.PAGE_SCOPE) %><br/>
    request域： <%=pageContext.getAttribute("message",PageContext.REQUEST_SCOPE) %><br/>
    session域： <%=pageContext.getAttribute("message",PageContext.SESSION_SCOPE) %><br/>
    context域：<%=pageContext.getAttribute("message",PageContext.APPLICATION_SCOPE) %><br/>
       %>
  </body>
</html>
