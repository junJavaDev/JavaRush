<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>
<c:set var="reqUrl">${rootPath}${Go.EDIT_QUEST_CONTENT}?${S.paramId}=${requestScope.quest.id}</c:set>
<c:set var="reqUrlFull">${reqUrl}&${S.paramQuestionId}=${requestScope.question.id}</c:set>

<div class="container-md">
    <div class="row">
        <div class="col col-md-5">
            <div class="card mx-auto my-3 quest-editor">
                <p class="fw-normal fs-6 fw-bold mt-2 mb-1 text-center">
                    ${requestScope.quest.name}
                    <a class="text-decoration-none"
                       href="${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${param.id}">
                        ${langPencilBtn}
                    </a>
                </p>
                <div class="my-custom-scrollbar h-100">

                    <div class="table-responsive mx-3">
                        <table class="table table-sm mb-0">
                            <thead>
                            <tr>
                                <th scope="col">${langQuestionId}</th>
                                <th scope="col">${langQuestionName}</th>
                                <th scope="col">${langQuestionConnection}</th>
                                <th scope="col">${langQuestionState}</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="question" items="${requestScope.quest.questions}" varStatus="loop">
                                <tr ${question.id == requestScope.question.id
                                        ?  "class='table-secondary'"
                                        :  ""} onclick="insertParam('${S.paramQuestionId}', '${question.id}');">
                                    <th scope="row">${loop.count}</th>
                                    <td>${question.name}</td>
                                    <td>${question.answers.size()}</td>
                                    <td>
                                        <fmt:message key="game_state.${question.gameState.name().toLowerCase()}"/>
                                    </td>
                                    <td><a class="link-primary text-decoration-none"
                                           href="javascript:postToUrl('${reqUrlFull}', {'${S.paramQuestionDelete}':'${question.id}'});">
                                            ${langTableDelete}
                                    </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="d-flex justify-content-between m-3">
                    <button class="btn btn-outline-danger" type="button"
                            onclick="postToUrl('${reqUrl}', {'${S.paramQuestDelete}':'${param.id}'});">
                        ${langQuestDeleteBtn}
                    </button>
                    <button class="btn btn-outline-secondary" type="button"
                            onclick="postToUrl('${reqUrl}', {'${S.paramQuestionCreate}':'${langCreateBtn}'});">
                        ${langQuestionAddBtn}
                    </button>
                </div>
            </div>

        </div>

        <div class="col col-md-7">
            <div class="card my-3 quest-editor">
                <div class="my-custom-scrollbar">

                    <p class="fw-normal fs-6 fw-bold mt-2 mb-1 text-center">${requestScope.question.name}</p>

                    <form class="needs-validation mx-3" novalidate
                          action="${reqUrlFull}"
                          method="post" enctype="multipart/form-data">

                        <%---------------- Labels -------------%>
                        <div class="row">
                            <div class="col-md-5 ">
                                <label class="form-label" for="questionName">${langQuestionName}</label>
                            </div>
                            <div class="col-md-7">
                                <label class="form-label" for="textarea">${langQuestionText}</label>
                            </div>
                        </div>
                        <%---------------- /Labels -------------%>


                        <div class="row">
                            <div class="col-md-5">
                                <div class="h-100">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input id="questionName" name="${S.inputName}" type="text"
                                                   placeholder="${langQuestionNamePlaceholder}"
                                                   class="form-control form-control-sm input-md" required
                                                   value="${requestScope.question.name}">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 my-2 qe-img-box">
                                            <c:choose>
                                                <c:when test="${requestScope.question.image != null}">
                                                    <img src="${rootPath}${S.imgDir}${requestScope.question.image}"
                                                         class="img-thumbnail d-block mx-auto quest-editor-image"
                                                         alt="${langImage}">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="${rootPath}${S.imgDir}${S.defaultImage}"
                                                         class="img-thumbnail d-block mx-auto quest-editor-image"
                                                         alt="${langImage}">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-7 mb-2">
                                <div class="h-100">
                                    <textarea class="form-control quest-editor-text" id="textarea"
                                              name="${S.inputText}"
                                              placeholder="${langQuestionTextPlaceholder}"
                                              required>${requestScope.question.text}</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-6">
                                <label class="form-label my-2" for="questionImage">${langImage}</label>
                                <input id="questionImage" name="${S.inputImage}" type="file"
                                       class="form-control form-control-sm input-md">
                            </div>
                            <div class="col-md-3">
                                <!-- Select Basic -->
                                <label class="form-label my-2"
                                       for="questionGameState">${langQuestionState}</label>
                                <select id="questionGameState" name="${S.inputGameState}"
                                        class="form-select form-select-sm">
                                    <c:forEach items="${GameState.values()}" var="gameState">
                                        <option value="${gameState}" ${gameState==requestScope.question.gameState?"selected":""}>
                                            <fmt:message key="game_state.${gameState.name().toLowerCase()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Button -->
                            <div class="col-md-3 align-self-end text-end">
                                <button class="btn btn-outline-secondary" id="update" name="${S.paramQuestionUpdate}">
                                    ${langSaveBtn}
                                </button>
                            </div>
                        </div>
                    </form>
                    <hr>

                    <c:choose>
                        <c:when test='${requestScope.question.gameState==GameState.PLAY}'>
                            <div class="mx-3 align-middle">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="table-responsive">
                                            <table class="table table-sm mb-0 text-sm">
                                                <thead>
                                                <tr>
                                                    <th scope="col">${langAnswerName}</th>
                                                    <th scope="col">${langAnswerNextQuestion}</th>
                                                    <th scope="col"></th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <c:forEach var="entry" items="${requestScope.answers}">

                                                    <tr>
                                                        <td>${entry.key.text}</td>
                                                        <td><a class="link-primary text-decoration-none"
                                                                <c:set var="questionIndex">${requestScope.quest.questions.indexOf(entry.value)}</c:set>
                                                               href="javascript:insertParam('${S.paramQuestionId}', '${entry.value.id}');">
                                                                ${questionIndex+1} ${entry.value.name}
                                                        </a></td>
                                                        <td><a class="link-primary text-decoration-none"
                                                               href="javascript:postToUrl('${reqUrlFull}', {'${S.paramAnswerDelete}':'${entry.key.id}'});">
                                                                ${langTableDelete}
                                                        </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-md-5">

                                        <form class="needs-validation"
                                              action="${reqUrlFull}"
                                              method="post" novalidate>

                                            <label class="form-label" for="answerText">${langAnswerText}</label>
                                            <input id="answerText" name="${S.inputAnswer}" type="text"
                                                   placeholder="${langAnswerTextPlaceholder}"
                                                   class="form-control form-control-sm input-md mb-1" required>

                                            <select class="form-select form-select-sm" name="${S.inputNextQuestionId}"
                                                    aria-label="" required>
                                                <option selected disabled value="">${langChooseQuestion}</option>
                                                <c:forEach var="question" items="${requestScope.quest.questions}"
                                                           varStatus="loop">
                                                    <c:if test="${question.id != requestScope.question.id}">
                                                        <option value="${question.id}">${loop.count} ${question.name}</option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>

                                            <div class="d-flex flex-column align-items-end my-3">
                                                <button class="btn btn-outline-secondary" type="submit"
                                                        id="submitAnswer" value="${requestScope.question.id}" name="${S.paramAnswerCreate}">
                                                        ${langAddAnswer}
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p class="fw-normal fs-6 text-center mt-3">${langCantAddAnswer}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/components/footer.jsp" %>