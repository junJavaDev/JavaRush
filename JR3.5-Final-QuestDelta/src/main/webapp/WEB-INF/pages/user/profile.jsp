<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">
    <h5 class="card-title my-3">${requestScope.user.login}</h5>

    <img src="${rootPath}${S.imgDir}${requestScope.user.avatar != null ? requestScope.user.avatar : S.defaultAvatar}"
         class="img-thumbnail rounded d-block profile-image"
         alt="${langAvatarLabel}">

    <table class="table table-sm mt-2 mb-0 w400">
        <tr>
            <td class="text-end"><b>${langId}</b></td>
            <td class="text-start">${requestScope.user.id}</td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langLogin}</b></td>
            <td class="text-start">${requestScope.user.login}</td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langRole}</b></td>
            <td class="text-start">${requestScope.user.role}</td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langLanguage}</b></td>
            <td class="text-start"><fmt:message key="language.${requestScope.user.language.name().toLowerCase()}"/></td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langQuestsCreated}</b></td>
            <td class="text-start">${requestScope.user.quests}</td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langProfileWins}</b></td>
            <td class="text-start">${requestScope.wins}</td>
        </tr>
        <tr>
            <td class="text-end w200"><b>${langProfileLoses}</b></td>
            <td class="text-start">${requestScope.loses}</td>
        </tr>
    </table>

    <c:if test="${sessionScope.user.id == param.id && sessionScope.role != Role.ADMIN}">
        <button class="btn btn-outline-secondary mx-auto my-3" type="button"
                onclick="document.location='${rootPath}${Go.EDIT_PROFILE}?${S.paramId}=${param.id}'">
                ${langEditProfile}
        </button>
    </c:if>
    <c:if test="${sessionScope.role == Role.ADMIN}">
        <button class="btn btn-outline-secondary mx-auto my-3" type="button"
                onclick="document.location='${rootPath}${Go.EDIT_USER}?${S.paramId}=${param.id}'">
                ${langEditUser}
        </button>
    </c:if>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>