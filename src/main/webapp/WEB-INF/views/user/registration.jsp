<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registration</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/login_registration.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


</head>

<body>
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav float-right">
                <a href="<c:url value='/books'/>" style="margin-right: 10px" class="list-group-item">All Books</a>
            </ul>
        </nav>
        <h3 class="text-muted">Online library</h3>
    </div>
</div>
<div class="container">
    <form:form method="POST" modelAttribute="userForm" class="form-signin" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Create account</h2>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Name"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="surname">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="surname" class="form-control" placeholder="Surname"
                            autofocus="true"></form:input>
                <form:errors path="surname"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="confirmPassword" class="form-control"
                            placeholder="Confirm password"></form:input>
                <form:errors path="confirmPassword"></form:errors>
            </div>
        </spring:bind>
        <div class="form-group">Avatar <input type="file" class="button" name="file_image" ></div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
