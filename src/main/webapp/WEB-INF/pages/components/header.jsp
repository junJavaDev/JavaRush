<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang/language"/>
<fmt:message key="header.admin_cp" var="headerAdminCP"/>
<fmt:message key="header.profile" var="headerProfile"/>
<fmt:message key="header.logout" var="headerLogout"/>
<fmt:message key="header.login" var="headerLogin"/>
<fmt:message key="header.signup" var="headerSignup"/>
<fmt:message key="header.language" var="headerLanguage"/>
<fmt:message key="header.users_edit" var="usersEdit"/>
<fmt:message key="header.user_create" var="userCreate"/>

<c:set var="rootPath">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="${rootPath}/css/custom.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>

<%--    Navbar--%>
<nav class="navbar navbar-expand-lg bg-light">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <div class="container-md">

            <div class="d-flex flex-row justify-content-between">

                <div class="d-flex flex-column">

<%--                    <c:if test="${sessionScope.role.equals('ADMIN') || sessionScope.role.equals('MODERATOR')}">--%>
                        <%-- Start navbar position --%>
                        <ul class="nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                        ${headerAdminCP}
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="${rootPath}/users">${usersEdit}</a></li>
                                    <li><a class="dropdown-item" href='${rootPath}/user?id=0'>${userCreate}</a></li>
                                    <li><a class="dropdown-item" href='${rootPath}/questsEdit'>${userCreate}</a></li>
                                </ul>
                            </li>
                        </ul>
<%--                    </c:if>--%>
                </div>

                <div class="d-flex flex-column">
                    <%-- Middle navbar position --%>
                    <ul class="nav mx-auto">
                        <%@ include file="navbarMenuList.jsp" %>
                    </ul>
                </div>

                <div class="d-flex flex-column">
                    <%-- End navbar position --%>
                    <ul class="nav">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <li><a href="${rootPath}/profile?id=${sessionScope.user.id}"
                                       class="nav-link px-2 link-dark"><b>${sessionScope.user.login}</b></a></li>
                                <li><a href="${pageContext.request.contextPath}/logout"
                                       class="nav-link px-2 link-dark">${headerLogout}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageContext.request.contextPath}/login"
                                       class="nav-link px-2 link-dark">${headerLogin}</a></li>
                                <li><a href="${pageContext.request.contextPath}/signup"
                                       class="nav-link px-2 link-dark">${headerSignup}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <%-- Language select --%>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                ${headerLanguage}
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="javascript:insertParam('lang', 'RU');">RU</a></li>
                                <li><a class="dropdown-item" href="javascript:insertParam('lang', 'EN');">EN</a></li>
                                <%--                        <li><hr class="dropdown-divider"></li>--%>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</nav>

<%--</div>--%>
