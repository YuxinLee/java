<%--
  Created by IntelliJ IDEA.
  User: leeyuxin
  Date: 19-8-21
  Time: 下午10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="frmLogin" action="${pageContext.request.contextPath }/login" method="post">
        <table align="center" border="1">
            <tr>
                <td>用户名</td>
                <td> <input type="text" name="userName"> </td>
            </tr>

            <tr>
                <td>密码</td>
                <td> <input type="password" name="pwd"> </td>
            </tr>

            <tr>
                <td> <input type="submit" value="亲，点我登陆！"> </td>
            </tr>
        </table>
    </form>
</body>
</html>
