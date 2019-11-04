<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	
  	<!-- 引入头部页面 -->
  	<jsp:include page="/public/head.jsp"></jsp:include>
  
  	<!-- 在线用户 -->
  	<table align="center" border="1" width="80%" cellpadding="3" cellspacing="0">
  		<tr>
  			<td colspan="2" align="center"><h3>在线列表展示：</h3></td>
  		</tr>
  		<tr>
  			<td>编号</td>
  			<td>员工名称</td>
  		</tr>
  		<c:if test="${not empty applicationScope.onlineList}">
	  		<c:forEach var="admin" items="${applicationScope.onlineList}">
		  		<tr>
		  			<td>${admin.id }</td>
		  			<td>${admin.userName }</td>
		  		</tr>
	  		</c:forEach>
  		</c:if>
  	</table>
  </body>
</html>
