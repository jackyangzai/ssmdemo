<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/25
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%
    request.setAttribute("a", request.getParameter("role"));
//    request.setAttribute("user",request.getParameter("user"));
%>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<h1>菜单列表</h1>
<ul id="Ul1">
    <c:if test="${a=='1'}">
        <li><a href="../list.html">账号管理</a></li>
    </c:if>
    <c:if test="${a=='1'}">
        <li><a href="../drug/list.do">药品目录维护</a></li>
    </c:if>
    <c:if test="${a=='1'}">
        <li><a href="../stock.html">库存查询</a></li>
    </c:if>
    <c:if test="${a=='1'||a==2}">
        <li><a href="/drug/toInOutStock.do">药品出入库</a></li>
    </c:if>

</ul>
</body>
</html>
