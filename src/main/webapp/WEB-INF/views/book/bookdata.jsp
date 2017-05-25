<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
  <meta charset="utf-8">
  <title>About book</title>

  <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/resources/css/main.css" rel="stylesheet">
  <script src="${contextPath}/resources/js/jquery-3.2.1.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${contextPath}/resources/js/rating.js"></script>
  <script src="${contextPath}/resources/js/bookdate.js"></script>
  <script>
    var onImage = '${contextPath}/resources/image/star-on.svg';
    var offImage = '${contextPath}/resources/image/star-off.svg';
    setImage(onImage,offImage);
    setUrl('http://${pageContext.request.serverName}:${pageContext.request.serverPort}/comment/add/${book.id}',
        'http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/rate/${book.id}');
  </script>
</head>
<body>
<div class="container">

  <div class="header clearfix">
    <nav>
      <ul class="nav float-right">
        <%@include file="_header.jsp" %>
        <c:if test="${isAdmin}">
          <button type="button" style="margin-right: 10px" class="list-group-item" data-toggle="modal" data-target="#addEditModal">
            Edit
          </button>
        </c:if>
      </ul>
    </nav>
    <h3 class="text-muted">Online library</h3>
  </div>

</div>


<div class="container">

  <div class="card card-block w-75" style="margin-top:20px" >
    <h3 class="card-title">${book.name}</h3>
    <div class="row">
      <div class="col-sm-6">
        <%@include file="_book.jsp" %>
        <p class="card-text"><strong>About: </strong><br> ${book.description}</p>
        <p>
            <a href="<c:url value='http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/download/${book.id}'/>">Download</a>&nbsp; &nbsp;
        </p>
      </div>
    </div>
  </div>

  <c:if test="${isLogin}">
      <p>
        <h3>My rating</h3>
        <ul id="user_rate" value="${userRate}"></ul>
      </p>
  </c:if>
</div>

<div class="container">
<h2>Comments</h2>
  <div id="comments">
    <c:if test="${!empty comments}">
      <c:forEach items="${comments}" var="comment">
        <%@include file="_comment.jsp" %>
      </c:forEach>
    </c:if>
  </div>
<c:if test="${isLogin}">
  <form style="width:500px;margin: auto" name="form_comment">
    <p><textarea cols="50"   class="form-control" rows="4" name="comment" id="text_area_comment"></textarea></p>
    <input type="button" id="button_send_comment" class="btn btn-default"  value="Send"/>
  </form>
</c:if>
<c:if test="${!isLogin}">
  <p>Register or sign in to comment</p>
</c:if>
</div>
</div>


<div class="modal fade bd-example-modal-lg" id="addEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog  modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Edit</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="/books/add" method="post" commandName="book" id="nameform" enctype="multipart/form-data">
          <input type="hidden" value="${book.id}" name="id" id="update_dialog_id">
          <table>
            <tr>
              <td>Name</td> <td><input type="text" value="${book.name}" name="name" style="width:200px" ></td>
            </tr>
            <tr>
              <td>Author</td> <td><input type="text" value="${book.author}" name="author" style="width:200px"  ></td>
            </tr>

            <tr>
              <td>Genre</td> <td>
              <select name="genre_id">
                <option value="${book.genre.id}">${book.genre.genre}</option>
                <c:forEach items="${listGenres}" var="genre">
                  <option value="${genre.id}">${genre.genre}</option>
                </c:forEach>
              </select>
            </td>
            </tr>

            <tr>
              <td>Description</td> <td><textarea name="description" style="width:200px" >${book.description}</textarea></td>
            </tr>
            <tr>
              <td>Book</td> <td><input type="file" class="button" name="file_book" ></td>
            </tr>
            <tr>
              <td>Image</td> <td><input type="file" class="button" name="file_image" ></td>
            </tr>
          </table>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button  class="btn btn-primary" type="submit" form="nameform">Edit</button>
      </div>
    </div>
  </div>
</div>
</div>

</body>
</html>