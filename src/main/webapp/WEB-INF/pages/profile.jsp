<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card align-items-center mx-auto my-3" style="width: 400px">
    <h5 class="card-title my-3">${requestScope.user.login}</h5>

    <img src="images/${requestScope.user.avatar != null ? requestScope.user.avatar : "no_image.jpg"}"
         class="img-thumbnail rounded d-block" style="max-width: 300px; max-height: 300px"
         alt="${requestScope.user.avatar}">

    <table class="table my-3" style="width: 300px;">
        <tr>
            <td style="text-align: right; width: 150px"><b>Id:</b></td>
            <td style="text-align: left">${requestScope.user.id}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Login:</b></td>
            <td style="text-align: left">${requestScope.user.login}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Role:</b></td>
            <td style="text-align: left">${requestScope.user.role}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Language:</b></td>
            <td style="text-align: left">${requestScope.user.language}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Quests created:</b></td>
            <td style="text-align: left">${requestScope.user.quests.size()}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Wins:</b></td>
            <td style="text-align: left">${requestScope.wins}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 150px"><b>Loses:</b></td>
            <td style="text-align: left">${requestScope.loses}</td>
        </tr>
    </table>

    <button class="btn btn-outline-secondary mx-auto my-3" type="button"
            onclick="document.location='${rootPath}/user?id=${sessionScope.user.id}'">
        Редактировать профиль
    </button>
</div>

<%@ include file="components/footer.jsp" %>