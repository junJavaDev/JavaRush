<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>
<c:set var="game">${requestScope.game}</c:set>


<div class="card align-items-center mx-auto my-3 w500">
    <img class="img-thumbnail quest-image mt-3 mx-3"
         src="${rootPath}${S.imgDir}${not empty requestScope.game.question.image
         ? requestScope.game.question.image
         : S.defaultImage}" alt="${langImage}">
    <div class="card-body w400">

        <p class="card-text mb-2">${requestScope.game.question.text}</p>

        <form action="${rootPath}${Go.PLAY}?${S.paramId}=${requestScope.game.questId}" method="post">
            <div class="row">
                <c:choose>
                    <c:when test="${not empty requestScope.game.question.answers}">
                        <c:forEach var="answer" items="${requestScope.game.question.answers}">
                            <div class="col-md-12">
                                <button class="btn btn-outline-secondary m-1" type="submit" name="${S.inputAnswer}"
                                        value="${answer.id}">
                                        ${answer.text}
                                </button>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-md-12">
                            <button class="btn btn-outline-secondary m-1" type="submit" name="${S.inputAnswer}"
                                    value="${S.playStartAgainValue}">
                                    ${langStartAgainBtn}
                            </button>
                        </div>
                        <div class="col-md-12">
                            <button class="btn btn-outline-secondary m-1" type="submit" name="${S.inputAnswer}"
                                    value="${S.playCompleteValue}">
                                    ${langCompleteQuestBtn}
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>