<%--
  Created by IntelliJ IDEA.
  User: leeyuxin
  Date: 19-8-21
  Time: 下午10:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h1>欢迎你，${sessionScope.loginInfo.userName }</h1>

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
