<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang/messages"/>
<fmt:message key="label.admin_cp" var="admin_cp"/>
<fmt:message key="label.home" var="home"/>
<fmt:message key="label.quests" var="quests"/>
<fmt:message key="label.create" var="create"/>
<fmt:message key="label.play" var="play"/>
<fmt:message key="label.statistic" var="statistic"/>
<fmt:message key="label.about" var="about"/>
<fmt:message key="label.profile" var="profile"/>
<fmt:message key="label.logout" var="logout"/>
<fmt:message key="label.login" var="login"/>
<fmt:message key="label.signup" var="signup"/>
<fmt:message key="label.language" var="language"/>


<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
<%--    Navbar--%>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03"
                aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <%-- Start navbar position --%>
            <ul class="nav">
                <li class="nav-item">
                    <a href="#" class="nav-link">${admin_cp}</a>
                </li>
            </ul>
            <%-- Middle navbar position --%>
            <ul class="nav mx-auto">
                <li class="nav-item">
                    <a class="nav-link link-dark" href="${pageContext.request.contextPath}">${home}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="#">${quests}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="#">${create}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="#">${play}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="#">${statistic}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="#">${about}</a>
                </li>
            </ul>
            <%-- End navbar position --%>
            <ul class="nav">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="${pageContext.request.contextPath}/profile"
                               class="nav-link px-2 link-dark">${profile}</a></li>
                        <li><a href="${pageContext.request.contextPath}/logout"
                               class="nav-link px-2 link-dark">${logout}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/login"
                               class="nav-link px-2 link-dark">${login}</a></li>
                        <li><a href="${pageContext.request.contextPath}/signup"
                               class="nav-link px-2 link-dark">${signup}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <%-- Language select --%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${language}
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="javascript:insertParam('locale', 'RU');">RU</a></li>
                        <li><a class="dropdown-item" href="javascript:insertParam('locale', 'EN');">EN</a></li>
                        <%--                        <li><hr class="dropdown-divider"></li>--%>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
