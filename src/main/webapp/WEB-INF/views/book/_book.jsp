<div class="row">
    <div class="col-sm-6">
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
        <c:if test="${isBookData}">
            <p class="card-text"><strong>About: </strong><br> ${book.description}</p>
            <p>
                <a href="<c:url value='http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/download/${book.id}'/>">Download</a>&nbsp; &nbsp;
            </p>
        </c:if>
        <c:if test="${isBooks}">
            <c:if test="${isAdmin}">
                <p>
                        <a href="<c:url value='http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/remove/${book.id}'/>">Delete</a>&nbsp; &nbsp;
                </p>
            </c:if>
        </c:if>
    </div>
</div>