<div class="col-sm-6">
    <figure>
        <c:if test="${empty user.imageUrl}">
            <img src="${contextPath}/resources/image/def_avatar.png" style="max-height: 200px" class="img-thumbnail" >
        </c:if>
        <c:if test="${!empty user.imageUrl}">
            <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/user/image/${user.id}" style="max-height: 200px"  class="img-thumbnail" >
        </c:if>
    </figure>
</div>
<div class="col-sm-6">
    <p class="card-text"><strong>Name: </strong> ${user.name}</p>
    <p class="card-text"><strong>Surname: </strong> ${user.surname}</p>
    <p class="card-text"><strong>Email: </strong> ${user.email}</p>
    <p class="card-text"><strong>City: </strong> ${user.city}</p>
    <p class="card-text"><strong>Country: </strong> ${user.country}</p>
</div>
