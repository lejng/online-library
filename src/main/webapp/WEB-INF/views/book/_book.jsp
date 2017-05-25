<figure>
    <c:if test="${empty book.imageUrl}">
        <img src="${contextPath}/resources/image/book.png" height="200px" class="img-thumbnail" >
    </c:if>
    <c:if test="${!empty book.imageUrl}">
        <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/image/${book.id}" style="height: 200px" class="img-thumbnail"  >
    </c:if>
</figure>
</div>
<div class="col-sm-6">
    <p class="card-text"><strong>Author: </strong> ${book.author}</p>
    <p class="card-text"><strong>Genre: </strong> ${book.genre.genre}</p>
    <p id="book_rating"><%@include file="_rating.jsp" %></p>
