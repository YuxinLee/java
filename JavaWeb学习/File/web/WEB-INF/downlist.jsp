<%--
  Created by IntelliJ IDEA.
  User: leeyuxin
  Date: 19-8-31
  Time: 下午5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>下载列表</title>
</head>
<body>

<table border="1" align="center">
    <tr>
        <th>序号</th>
        <th>文件名</th>
        <th>操作</th>
    </tr>
    <c:forEach var="en" items="${requestScope.fileNames}" varStatus="vs">
        <tr>
            <td>${vs.count }</td>
            <td>${en.value }</td>
            <td>
                    <%--<a href="${pageContext.request.contextPath }/fileServlet?method=down&..">下载</a>--%>
                <!-- 构建一个地址  -->
                <c:url var="url" value="fileServlet">
                    <c:param name="method" value="down"></c:param>
                    <c:param name="fileName" value="${en.key}"></c:param>
                </c:url>
                <!-- 使用上面地址 -->
                <a href="${url }">下载</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
