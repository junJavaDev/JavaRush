<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

Здесь будет запуск игр
<div class="container">
    <br>
    GAME SESSION<br>
    Long id [${sessionScope.gameSession.id}] <br>
    Long userId [${sessionScope.gameSession.userId}] <br>
    Long questId [${sessionScope.gameSession.questId}] <br>
    GameState gameState [${sessionScope.gameSession.gameState}] <br>
    ZonedDateTime startTime [${sessionScope.gameSession.startTime}] <br>
    ZonedDateTime lastSeen [${sessionScope.gameSession.lastSeen}] <br>
    Long currentQuestionId [${sessionScope.gameSession.currentQuestionId}] <br>

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

    QUEST<br>
    Long id [${sessionScope.quest.id}] <br>
    String name [${sessionScope.quest.name}] <br>
    Long authorId [${sessionScope.quest.authorId}] <br>
    String text [${sessionScope.quest.text}] <br>
    String image [${sessionScope.quest.image}] <br>
    Collection questions [${sessionScope.quest.questions}] <br>






</div>
<%@ include file="components/footer.jsp" %>