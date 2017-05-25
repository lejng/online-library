<c:if test="${isLogin}">
    <a href="<c:url value='/user'/>" class="list-group-item" style="margin-right: 10px">My profile</a>
</c:if>
<c:if test="${isLogin}">
    <a href="<c:url value='/logout'/>" class="list-group-item" style="margin-right: 10px">Log out</a>
</c:if>
<c:if test="${!isLogin}">
    <a href="<c:url value='/login'/>" class="list-group-item" style="margin-right: 10px">Login</a>
</c:if>
<a href="<c:url value='/books'/>" style="margin-right: 10px" class="list-group-item">All Books</a>
