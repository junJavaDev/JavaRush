<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card align-items-center mx-auto my-3" style="width: 500px">
    <p class="fw-normal fs-4 my-3">Statistics:</p>

    <table class="table mb-4" style="width: 400px;">
        <tr>
            <td style="text-align: right; width: 230px">Users registered:</td>
            <td style="text-align: left">${requestScope.usersRegistered}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 230px">Games played:</td>
            <td style="text-align: left">${requestScope.gamesPlayed}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 230px">Quests created: </td>
            <td style="text-align: left">${requestScope.questCreated}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 230px">Questions created:</td>
            <td style="text-align: left">${requestScope.questionsCreated}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 230px">Answers created:</td>
            <td style="text-align: left">${requestScope.answersCreated}</td>
        </tr>
        <tr>
            <td style="text-align: right; width: 230px">
                Best player:<br>
                wins:
            </td>
            <td style="text-align: left; width: 230px">
                <a href="${rootPath}/profile?id=${requestScope.bestPlayerID}">
                ${requestScope.bestPlayerLogin}</a><br>
                ${requestScope.bestPlayerWins}</td>
        </tr>

        <tr>
            <td style="text-align: right; width: 230px">
                Worst player:<br>
                loses:
            </td>
            <td style="text-align: left; width: 230px">
                <a href="${rootPath}/profile?id=${requestScope.worstPlayerID}">
                    ${requestScope.worstPlayerLogin}</a><br>
                ${requestScope.worstPlayerLoses}</td>
        </tr>


        <tr>
            <td style="text-align: right; width: 230px">
                Most popular quest:<br>
                launches:
            </td>
            <td style="text-align: left; width: 230px">
                <a href="${rootPath}/play?questId=${requestScope.mostPopularQuestID}">
                    ${requestScope.mostPopularQuestName}</a><br>
                ${requestScope.mostPopularQuestLaunches}</td>
        </tr>

    </table>
</div>

<%@ include file="components/footer.jsp" %>