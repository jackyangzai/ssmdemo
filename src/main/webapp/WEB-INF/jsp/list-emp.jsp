<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/21
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    request.setAttribute("a", 20);
    request.setAttribute("b", 15);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<ul id="Ul1">
    <c:if test="${a>b}">
        <li><a href="list.html">账号管理</a></li>
    </c:if>
    <li><a href="">药品目录维护</a></li>
</ul>
<table border="1">
    <thead>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>薪水</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${list}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.salary}</td>
        </tr>
    </c:forEach>
    </tbody>


</table>
</body>
</html>
