<div class="col-sm-6">
    <figure>
        <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}/user/image/${user.id}" onerror="this.src = '${contextPath}/resources/image/def_avatar.png';" style="max-height: 200px"  class="img-thumbnail" >
    </figure>
</div>
<div class="col-sm-6">
    <table class="table table-bordered">
        <tr><td><strong>Name: </strong></td> <td>${user.name}</td></tr>
        <tr><td><strong>Surname: </strong></td><td> ${user.surname}</td></tr>
        <tr><td><strong>Email: </strong></td><td> ${user.email}</td></tr>
        <tr><td><strong>City: </strong></td><td> ${user.city}</td></tr>
        <tr><td><strong>Country: </strong></td><td> ${user.country}</td></tr>
    </table>
</div>
