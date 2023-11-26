<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">
    <p class="fw-normal fs-4 my-3">${langStatisticsPageTitle}</p>

    <table class="table mb-4 w400">
        <tr>
            <td class="text-end w200">${langUserRegistered}</td>
            <td class="text-start">${requestScope.usersRegistered}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langGamesPlayed}</td>
            <td class="text-start">${requestScope.gamesPlayed}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langQuestsCreated}</td>
            <td class="text-start">${requestScope.questCreated}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langQuestionsCreated}</td>
            <td class="text-start">${requestScope.questionsCreated}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langAnswersCreated}</td>
            <td class="text-start">${requestScope.answersCreated}</td>
        </tr>
        <tr>
            <td class="text-end w200">
                ${langBestPlayer}<br>
                ${langWins}
            </td>
            <td class="text-start">
                <a href="${rootPath}${Go.PROFILE}?${S.paramId}=${requestScope.bestPlayerID}">
                ${requestScope.bestPlayerLogin}</a><br>
                ${requestScope.bestPlayerWins}</td>
        </tr>

        <tr>
            <td  class="text-end w200">
                ${langWorstPlayer}<br>
                ${langLoses}
            </td>
            <td  class="text-start">
                <a href="${rootPath}${Go.PROFILE}?${S.paramId}=${requestScope.worstPlayerID}">
                    ${requestScope.worstPlayerLogin}</a><br>
                ${requestScope.worstPlayerLoses}</td>
        </tr>


        <tr>
            <td  class="text-end w200">
                ${langMostPopularQuest}<br>
                ${langLaunches}
            </td>
            <td  class="text-start ">
                <a href="${rootPath}${Go.PLAY}?${S.paramId}=${requestScope.mostPopularQuestID}">
                    ${requestScope.mostPopularQuestName}</a><br>
                ${requestScope.mostPopularQuestLaunches}</td>
        </tr>

    </table>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>