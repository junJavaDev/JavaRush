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

<c:set var="rootPath">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
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
                    <%-- Start navbar position --%>
                    <ul class="nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                ${headerAdminCP}
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${rootPath}/users">${usersEdit}</a></li>
                                <%--                            <li><a class="dropdown-item" href="javascript:insertParam('lang', 'EN');">EN</a></li>--%>
                            </ul>
                        </li>
                    </ul>
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
                                <li><a href="${rootPath}/profile"
                                       class="nav-link px-2 link-dark">${headerProfile}</a></li>
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
