<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
