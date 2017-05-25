<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 3/6/2017
  Time: 12:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful registration</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav float-right">
                <a href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/books" style="margin-right: 10px" class="list-group-item">All Books</a>
            </ul>
            <ul class="nav float-right">
                <a href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/login" style="margin-right: 10px" class="list-group-item">Login</a>
            </ul>
        </nav>
        <h3 class="text-muted">Online library</h3>
    </div>
    <p>You have been successfully registered.</p>
</div>
</body>
</html>
