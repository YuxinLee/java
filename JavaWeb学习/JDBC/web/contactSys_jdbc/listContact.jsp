<%--
  Created by IntelliJ IDEA.
  User: leeyuxin
  Date: 19-7-13
  Time: 下午9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>查询所有联系人</title>
</head>
<body>
    <center><h3>查询所有联系人</h3></center>
    <table align="center" border="1" width="700px">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>QQ</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${contacts}" var="con" varStatus="varSta">
            <tr>
                <td>${varSta.count}</td>
                <td>${con.name}</td>
                <td>${con.gender }</td>
                <td>${con.age }</td>
                <td>${con.phone }</td>
                <td>${con.email }</td>
                <td>${con.qq }</td>
                <td>
                    <a href="${pageContext.request.contextPath}/QueryContactServlet?id=${con.id}">修改</a>
                    &nbsp;<a href="${pageContext.request.contextPath}/DeleteContactServlet?id=${con.id}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="8" align="center">
                <a href="contactSys_jdbc/addContact.jsp">[添加联系人]</a>
            </td>
        </tr>
    </table>

</body>
</html>
