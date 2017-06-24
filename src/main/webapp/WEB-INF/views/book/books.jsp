<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setCharacterEncoding("UTF-8");%>

<html>
<head>

    <meta charset="utf-8">
    <title>Books</title>
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
                <%@include file="_header.jsp" %>
                <c:if test="${isAdmin}">
                    <a href="#" class="list-group-item" style="margin-right: 10px" data-toggle="modal" data-target="#addEditModal">Add</a>
                </c:if>
            </ul>
        </nav>
        <h3 class="text-muted">Online library</h3>
    </div>

</div>


<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
                <h1>Books</h1>
                <div id="books">
                    <c:if test="${!empty listBooks}">
                            <c:forEach items="${listBooks}" var="book">

                                <div class="card" style="margin-top:20px" id="${book.id}">
                                        <h3 class="card-header"><a href="<c:url value='/bookdata/${book.id}'/>" class="card-link">${book.name}</a></h3>
                                    <div class="card-block">
                                                <%@include file="_book.jsp" %>
                                    </div>
                                </div>
                            </c:forEach>
                    </c:if>
                    <c:if test="${empty listBooks}">
                        <p>Books not found</p>
                    </c:if>

                </div>
     </div><!--/.col-xs-12.col-sm-9-->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a class="list-group-item">
                    <h3>Find by genre</h3>
                </a>
                <c:forEach items="${listGenres}" var="genre">
                    <a href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/books/genre/${genre.id}" class="list-group-item">${genre.genre}</a>
                </c:forEach>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div>
  </div>
</div>



<div class="modal fade bd-example-modal-lg" id="addEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog  modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Add </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/books/add" method="POST"  commandName="book"  id="form_add_book" enctype="multipart/form-data">
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Name</label>
                        <div class="col-5">
                            <input  class="form-control" type="text" name="name"  required minlength="3" >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Author</label>
                        <div class="col-5">
                            <input  class="form-control" type="text" name="author"  required minlength="3" >
                        </div>
                    </div>

                    <div class="form-group row">
                        <label  class="col-2 col-form-label">Genre</label>
                        <div class="col-5">
                            <select name="genre_id" class="custom-select">
                                <c:forEach items="${listGenres}" var="genre">
                                <option value="${genre.id}">${genre.genre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-2 col-form-label">Description</label>
                        <div class="col-5">
                            <textarea cols="50"  class="form-control" rows="4" name="description" required minlength="10"></textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Book</label>
                        <div class="col-5">
                             <input type="file" class="button" name="file_book" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Image</label>
                        <div class="col-5">
                            <input type="file" class="button" name="file_image" >
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button  class="btn btn-primary" form="form_add_book" >Add</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
