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
        <table class="table table-bordered">

            <tr><td><strong>Author: </strong></td> <td> ${book.author}</td></tr>
            <tr><td><strong>Genre: </strong></td> <td> ${book.genre.genre}</td></tr>
            <tr id="book_rating"> <%@include file="_rating.jsp" %></tr>
                <c:if test="${isBookData}">
                    <tr><td><strong>About: </strong></td><td> ${book.description}</td></tr>
                </c:if>
        </table>
                <c:if test="${isBookData}">
                    <p>
                        <a href="<c:url value='http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/download/${book.id}'/>" class="btn btn-primary">Download</a>
                    </p>
                </c:if>
                <c:if test="${isBooks}">
                    <c:if test="${isAdmin}">
                        <p>
                            <a href="<c:url value='http://${pageContext.request.serverName}:${pageContext.request.serverPort}/book/remove/${book.id}'/>" class="btn btn-primary">Delete</a>
                        </p>
                    </c:if>
                </c:if>
    </div>
</div>