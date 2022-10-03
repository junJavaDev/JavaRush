<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">
    <h5 class="card-title my-3">${requestScope.question.name}</h5>
    <img class="img-thumbnail quest-image"
         src="${rootPath}${s.imgDir}${requestScope.question.image}" alt="image">
    <div class="card-body m-2 w400">

        <p class="card-text">${requestScope.question.text}</p>

        <form action="${rootPath}${s.play}?${s.paramId}=${requestScope.quest.id}" method="post">
            <div class="row">
                <c:choose>
                    <c:when test="${not empty requestScope.question.answers}">
                        <c:forEach var="answer" items="${requestScope.question.answers}">
                            <div class="col-md-12">
                                <button class="btn btn-outline-secondary m-2" type="submit" name="answer"
                                        value="${answer.id}">
                                        ${answer.text}
                                </button>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-md-12">
                            <button class="btn btn-outline-secondary m-2" type="submit" name="${s.inputAnswer}"
                                    value="-1">
                                    ${langStartAgainBtn}
                            </button>
                        </div>
                        <div class="col-md-12">
                            <button class="btn btn-outline-secondary m-2" type="submit" name="${s.inputAnswer}"
                                    value="-2">
                                    ${langCompleteQuestBtn}
                            </button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
</div>

<%@ include file="components/footer.jsp" %>