<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<div class="header clearfix">
    <nav>
        <ul class="nav float-right">
            <c:if test="${isLogin}">
                <a href="<c:url value='/logout'/>" class="list-group-item" style="margin-right: 10px">Log out</a>
            </c:if>
            <c:if test="${!isLogin}">
                <a href="<c:url value='/login'/>" class="list-group-item" style="margin-right: 10px">Login</a>
            </c:if>
            <c:if test="${isAdmin}">
                <button type="button" style="margin-right: 10px" class="list-group-item" data-toggle="modal" data-target="#addEditModal">
                    Add/Edit
                </button>
            </c:if>
            <a href="<c:url value='/books'/>" style="margin-right: 10px" class="list-group-item">All Books</a>
            <c:if test="${isLogin}">
                <a href="<c:url value='/user'/>" class="list-group-item" style="margin-right: 10px">My profile</a>
            </c:if>
        </ul>
    </nav>
    <h3 class="text-muted">Online library</h3>
</div>
