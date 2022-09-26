<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card align-items-center mx-auto my-3" style="width: 500px">

    <p class="fw-normal fs-4 my-3">About this site</p>

    <img src="images/pacific.jpg"
         class="img-thumbnail rounded d-block mx-auto mb-3" style="max-width: 400px; max-height: 400px"
         alt="${sessionScope.user.avatar}">

        <ul class="list-group list-group-flush mb-4">
            <li class="list-group-item">Автор: Огарков Александр</li>
            <li class="list-group-item">JavaRush университет, группа Delta</li>
        </ul>
</div>
<%@ include file="components/footer.jsp" %>