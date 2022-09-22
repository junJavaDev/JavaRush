<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card-group ">
    <%--    <br>--%>
    <%--    GAME SESSION<br>--%>
    <%--    Long id [${requestScope.gameSession.id}] <br>--%>
    <%--    Long userId [${requestScope.gameSession.userId}] <br>--%>
    <%--    Long questId [${requestScope.gameSession.questId}] <br>--%>
    <%--    GameState gameState [${requestScope.gameSession.gameState}] <br>--%>
    <%--    ZonedDateTime startTime [${requestScope.gameSession.startTime}] <br>--%>
    <%--    ZonedDateTime lastSeen [${requestScope.gameSession.lastSeen}] <br>--%>
    <%--    Long currentQuestionId [${requestScope.gameSession.currentQuestionId}] <br>--%>

    <%--    USER<br>--%>
    <%--    Long id [${sessionScope.user.id}] <br>--%>
    <%--    String avatar [${sessionScope.user.avatar}] <br>--%>
    <%--    String login [${sessionScope.user.login}] <br>--%>
    <%--    String password [${sessionScope.user.password}] <br>--%>
    <%--    Role role [${sessionScope.user.role}] <br>--%>
    <%--    Language language [${sessionScope.user.language}] <br>--%>
    <%--    Collection [${sessionScope.user.quests}] <br>--%>
    <%--    Collection gameSessions [${sessionScope.user.gameSessions.get(0).lastSeen}] <br>--%>
    <%--    <br>--%>

    <%--    QUEST<br>--%>
    <%--    Long id [${sessionScope.quest.id}] <br>--%>
    <%--    String name [${sessionScope.quest.name}] <br>--%>
    <%--    Long authorId [${sessionScope.quest.authorId}] <br>--%>
    <%--    String text [${sessionScope.quest.text}] <br>--%>
    <%--    String image [${sessionScope.quest.image}] <br>--%>
    <%--    Collection questions [${sessionScope.quest.questions}] <br>--%>
    <%--    <br>--%>
    <%--    QUESTION<br>--%>
    <%--    <b>Long id: </b>[${requestScope.question.id}]<br>--%>
    <%--        <b>Long questId: </b>[${requestScope.question.questId}]<br>--%>
    <%--    <b>GameState gameState: </b>[${requestScope.question.gameState}]<br>--%>
    <%--    <b>String name: </b>[${requestScope.question.name}]<br>--%>
    <%--    <b>String text: </b>[${requestScope.question.text}]<br>--%>
    <%--    <b>String image: </b>[${requestScope.question.image}]<br>--%>
    <%--    <b>Collection answers: </b>[${requestScope.question.answers}]<br>--%>
    <%--    <br>--%>
    <%--        <img src="..." class="rounded mx-auto d-block" alt="...">--%>
    <img width="450px" class="img-thumbnail"
         src="${rootPath}/images/quests/${requestScope.question.questId}/${requestScope.question.image}" alt="wtf">
    <div class="card-body m-2">
        <h5 class="card-title">${requestScope.question.name}</h5>
        <p class="card-text">${requestScope.question.text}</p>

        <c:forEach var="answer" items="${requestScope.question.answers}">
            <div class="d-flex flex-row">
                <button class="btn btn-outline-secondary m-2" type="button"
                        onclick="insertParam('questionId', '${answer.nextQuestionId}');">
                        ${answer.text}
                </button>
            </div>
        </c:forEach>

    </div>
</div>


</div>
<%@ include file="components/footer.jsp" %>