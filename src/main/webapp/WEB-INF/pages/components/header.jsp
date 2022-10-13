<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/variables.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>${langTitle}</title>
    <link rel="shortcut icon" href="${rootPath}/favicon.ico?" type="image/x-icon" />
    <link href="${rootPath}/css/custom.css" rel="stylesheet" type="text/css">
</head>
<body>

<%--    Navbar--%>
<div class="container-md ">

    <nav class="navbar bg-light">
        <div class="w-100">
            <div class="d-flex flex-row justify-content-between">
                <div class="d-flex flex-column">
                    <%-- Start navbar position --%>
                    <ul class="nav">
                        <c:if test="${sessionScope.role == Role.ADMIN}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                        ${langAdminCP}
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item"
                                           href="${rootPath}${Go.USERS}">${langUsersEdit}</a></li>
                                    <li><a class="dropdown-item"
                                           href="${rootPath}${Go.EDIT_QUESTS}">${langQuestsEdit}</a></li>
                                    <li><a class="dropdown-item"
                                           href='${rootPath}${Go.EDIT_USER}?${S.paramId}=${S.zero}'>${langUserCreate}</a></li>
                                    <li><a class="dropdown-item"
                                           href='${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${S.zero}'>${langQuestCreate}</a></li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.role == Role.USER}">
                            <li class="nav-item">
                                <a class="nav-link px-2 link-dark"
                                   href='${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${S.zero}'>${langQuestCreate}</a>
                            </li>
                        </c:if>
                    </ul>
                </div>

                <div class="d-flex flex-column">
                    <%-- Middle navbar position --%>
                    <ul class="nav mx-auto">
                        <%@ include file="/WEB-INF/pages/components/navbarMenuList.jsp" %>
                    </ul>
                </div>

                <div class="d-flex flex-column">
                    <%-- End navbar position --%>
                    <ul class="nav">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <li><a href="${rootPath}${Go.PROFILE}?${S.paramId}=${sessionScope.user.id}"
                                       class="nav-link px-2 link-dark"><b>${sessionScope.user.login}</b></a></li>
                                <li><a href="${rootPath}${Go.LOGOUT}"
                                       class="nav-link px-2 link-dark">${langLogout}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${rootPath}${Go.LOGIN}"
                                       class="nav-link px-2 link-dark">${langLogin}</a></li>
                                <li><a href="${rootPath}${Go.SIGNUP}"
                                       class="nav-link px-2 link-dark">${langSignup}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <%-- Language select --%>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                ${langLanguage}
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item"
                                       href="javascript:insertParam('${S.paramLang}', '${Language.RU}');">${langLanguageRU}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="javascript:insertParam('${S.paramLang}', '${Language.EN}');">${langLanguageEN}</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>
