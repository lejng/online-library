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

<script>
    window.onload = function(){
        var buttonSend = document.querySelector('#button_send');
        buttonSend.onclick = function(){
            var form = document.forms.form_edit_user;
            if(form.checkValidity() == false) {
                form.reportValidity();
                return;
            }
            var form = new FormData(form);
            var req = new XMLHttpRequest();
            var url = 'http://${pageContext.request.serverName}:${pageContext.request.serverPort}/user/edit';
            req.open("POST",url);
            req.send(form);
            req.onreadystatechange = function () {
                if(this.readyState != 4) return;
                if(this.status == 200){
                    var userCard = document.querySelector('#user_card');
                    $('#addEditModal').modal('hide');
                    userCard.innerHTML = '';
                    userCard.innerHTML = this.responseText;
                }
                if(this.status != 200) {
                    $('#addEditModal').modal('hide');
                    alert("can not update profile,check your internet connection");
                    console.log("error on url " + url);
                }

            }
        }
    }
</script>

<body>
<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav float-right">
                <c:if test="${isLogin}">
                    <a href="<c:url value='/logout'/>" class="list-group-item" style="margin-right: 10px">Log out</a>
                </c:if>
                <a href="<c:url value='/books'/>" style="margin-right: 10px" class="list-group-item">All Books</a>
                <a href="#" class="list-group-item" style="margin-right: 10px" data-toggle="modal" data-target="#addEditModal">Edit profile</a>
            </ul>
        </nav>
        <h3 class="text-muted">Online library</h3>
    </div>
</div>

<div class="container">

    <h3>My profile</h3>
    <div class="card card-block w-50" style="margin-top:20px" >
        <div class="row" id="user_card">
            <%@include file="_user.jsp" %>
        </div>
    </div>

</div>
<div class="container">

    <h3 style="margin-top: 10px">My books</h3>
    <c:if test="${!empty listBooks}">
        <c:forEach items="${listBooks}" var="book">
                <div class="card w-50" style="margin-top:20px" >
                    <h3 class="card-header"><a href="<c:url value='/bookdata/${book.book.id}'/>" class="card-link">${book.book.name}</a></h3>
                    <div class="card-block">
                        <div class="row">
                            <div class="col-sm-6">
                                <figure>
                                    <c:if test="${empty book.book.imageUrl}">
                                        <img src="${contextPath}/resources/image/book.png" height="200px" class="img-thumbnail" >
                                    </c:if>
                                    <c:if test="${!empty book.book.imageUrl}">
                                        <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/image/${book.book.id}" style="height: 200px" class="img-thumbnail"  >
                                    </c:if>
                                </figure>
                            </div>
                            <div class="col-sm-6">
                                <table class="table table-bordered">
                                    <tr><td><strong>Author: </strong></td><td> ${book.book.author}</td></tr>
                                    <tr><td><strong>Genre: </strong></td> <td> ${book.book.genre.genre}</td></tr>
                                    <tr><td><strong>Status: </strong></td><td> ${book.status}</td></tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
        </c:forEach>
    </c:if>
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
                <form name="form_edit_user" commandName="user" id="nameform" enctype="multipart/form-data">
                    <input type="hidden" value="${user.id}" name="id" id="update_dialog_id">
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Name</label>
                        <div class="col-5">
                            <input value="${user.name}" required minlength="3" type="text" class="form-control" name="name" >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Surname</label>
                        <div class="col-5">
                            <input type="text" value="${user.surname}" required minlength="3" class="form-control" name="surname">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">City</label>
                        <div class="col-5">
                            <input value="${user.city}" type="text" class="form-control" required minlength="3"   name="city" >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Country</label>
                        <div class="col-5">
                            <input type="text" value="${user.country}" class="form-control" required minlength="3" name="country">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Avatar</label>
                        <div class="col-5">
                            <input type="file" class="button" name="file_image" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button  class="btn btn-primary" id="button_send">Save</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>