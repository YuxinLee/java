<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	
  	<!-- 引入头部页面 -->
  	<jsp:include page="/public/head.jsp"></jsp:include>
  	
  	<!-- 列表展示数据 -->
  	<table align="center" border="1" width="80%" cellpadding="3" cellspacing="0">
  		<tr>
  			<td>序号</td>
  			<td>编号</td>
  			<td>员工名称</td>
  		</tr>
  		<c:if test="${not empty requestScope.listEmp}">
	  		<c:forEach var="emp" items="${requestScope.listEmp}" varStatus="vs">
		  		<tr>
		  			<td>${vs.count }</td>
		  			<td>${emp.empId }</td>
		  			<td>${emp.empName }</td>
		  		</tr>
	  		</c:forEach>
  		</c:if>
  	</table>
  </body>
</html>
