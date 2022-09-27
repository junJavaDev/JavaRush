<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card align-items-center mx-auto my-3" style="width: 500px">

    <p class="fw-normal fs-4 my-3">About this site</p>

    <img src="images/pacific.jpg"
         class="img-thumbnail rounded d-block mx-auto mb-3" style="max-width: 400px; max-height: 400px"
         alt="${sessionScope.user.avatar}">

    <table class="table mb-4" style="width: 400px;">
        <tr>
            <td style="text-align: right; width: 200px">Группа:</td>
            <td style="text-align: left">Delta</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 200px">Университет:</td>
            <td style="text-align: left">JavaRush</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 200px">Автор сайта:</td>
            <td style="text-align: left;">Огарков Александр</td>
        </tr>
    </table>
</div>
<%@ include file="components/footer.jsp" %>