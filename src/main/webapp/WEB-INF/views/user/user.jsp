<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <meta charset="utf-8">
    <title>My profile</title>
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
                <c:if test="${isLogin}">
                    <a href="<c:url value='/logout'/>" class="list-group-item" style="margin-right: 10px">Log out</a>
                </c:if>
                <a href="<c:url value='/books'/>" style="margin-right: 10px" class="list-group-item">All Books</a>
                <button type="button" style="margin-right: 10px" class="list-group-item" data-toggle="modal" data-target="#addEditModal">
                    Edit profile
                </button>
            </ul>
        </nav>
        <h3 class="text-muted">Online library</h3>
    </div>
</div>

<div class="container">

    <h3>My profile</h3>
    <div class="card card-block w-50" style="margin-top:20px" >
        <div class="row">
            <%@include file="_user.jsp" %>
        </div>
    </div>

</div>


<div class="modal fade bd-example-modal-lg" id="addEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog  modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Edit profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/user/edit" method="post" commandName="user" id="nameform" enctype="multipart/form-data">
                    <input type="hidden" value="${user.id}" name="id" id="update_dialog_id">
                    <table>
                        <tr>
                            <td>Name</td> <td><input value="${user.name}" required minlength="3" type="text" style="width:200px"    name="name" ></td>
                        </tr>
                        <tr>
                            <td>Surname</td> <td><input type="text" value="${user.surname}" required minlength="3" style="width:200px" name="surname"></td>
                        </tr>
                        <tr>
                            <td>City</td> <td><input value="${user.city}" type="text" style="width:200px" required minlength="3"   name="city" ></td>
                        </tr>
                        <tr>
                            <td>Country</td> <td><input type="text" value="${user.country}" style="width:200px" required minlength="3" name="country"></td>
                        </tr>
                        <tr>
                            <td>Avatar</td> <td><input type="file" class="button" name="file_image" ></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button  class="btn btn-primary" type="submit" form="nameform">Save</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>