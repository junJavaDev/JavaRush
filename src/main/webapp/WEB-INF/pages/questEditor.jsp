<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="login.login_label" var="loginLabel"/>
<fmt:message key="login.password_label" var="passwordLabel"/>
<fmt:message key="login.login_form_legend" var="loginFormLegend"/>

<fmt:message key="login.password_placeholder" var="passwordPlaceholder"/>
<fmt:message key="login.login_placeholder" var="loginPlaceholder"/>

<fmt:message key="login.sign_in" var="signIn"/>
<div class="container-md">
    <div class="row">
        <div class="col col-md-5">
            <div class="card mx-auto my-3" style="height: 570px">
                <p class="fw-normal fs-6 fw-bold mt-2 mb-1 text-center">${requestScope.quest.name}</p>
                <div class="my-custom-scrollbar h-100">
                    <div class="table-responsive mx-3">
                        <table class="table table-sm mb-0">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Название вопроса</th>
                                <th scope="col">🔗</th>
                                <th scope="col">Состояние</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="question" items="${requestScope.quest.questions}" varStatus="loop">
                                <tr ${question.id == requestScope.question.id
                                        ?  "class='table-dark'"
                                        :  ""} onclick="insertParam('questionIndex', '${loop.index}');">
                                    <th scope="row">${loop.count}</th>
                                    <td>${question.name}</td>
                                    <td>${question.answers.size()}</td>
                                    <td ${question.id == requestScope.question.id
                                            ?  "class='table-dark'"
                                            :  question.gameState == "LOSE"
                                            ?  "class='table-danger'"
                                            : question.gameState == "WIN"
                                            ?  "class='table-success'"
                                            :   ""}>
                                        <fmt:message key="game_state.${question.gameState.name().toLowerCase()}"/>
                                    </td>
                                    <td><a class="link-primary text-decoration-none"
                                           href="javascript:postToUrl('/quest-edit?id=${param.id}', {'questionDelete':'${question.id}'});">
                                        x
                                    </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="d-flex flex-column align-items-end m-3">
                    <button class="btn btn-outline-secondary" type="button"
                            onclick="postToUrl('/quest-edit?id=${param.id}', {'questionCreate':'create'});">
                        Добавить вопрос
                    </button>
                </div>


                <%--                <div class="d-flex flex-column m-3">--%>
                <%--                    <button class="btn btn-outline-secondary align-self-end text-end" type="button"--%>
                <%--                            onclick="document.location='${rootPath}/question-create?id=${requestScope.quest.id}'">--%>
                <%--                        Добавить вопрос--%>
                <%--                    </button>--%>
                <%--                </div>--%>
            </div>

        </div>

        <div class="col col-md-7">
            <div class="card my-3" style="height: 570px">
                <div class="my-custom-scrollbar">

                    <p class="fw-normal fs-6 fw-bold mt-2 mb-1 text-center">${requestScope.question.name}</p>

                    <form class="needs-validation mx-3" novalidate
                          action="${pageContext.request.contextPath}/quest-edit?id=${param.id}&questionIndex=${param.questionIndex}"
                          method="post" enctype="multipart/form-data">

                        <%---------------- Labels -------------%>
                        <div class="row">
                            <div class="col-md-5 ">
                                <label class="form-label" for="questionName">Название</label>
                            </div>
                            <div class="col-md-7">
                                <label class="form-label" for="textarea">Текст вопроса</label>
                            </div>
                        </div>
                        <%---------------- /Labels -------------%>


                        <div class="row">
                            <div class="col-md-5">

                                <div class="h-100">

                                    <div class="row">
                                        <div class="col-md-12">

                                            <input id="questionName" name="name" type="text"
                                                   placeholder="Введите название вопроса"
                                                   class="form-control form-control-sm input-md" required

                                                   value="${requestScope.question.name}">
                                        </div>
                                    </div>
                                    <div class="row">

                                        <div class="col-md-12 my-2">
                                            <c:choose>
                                                <c:when test="${requestScope.question.image != null}">
                                                    <img src="images/${requestScope.question.image}"
                                                         style="max-width: inherit; text-align: center; height: 180px"
                                                         class="img-thumbnail d-block mx-auto"
                                                         alt="${requestScope.question.image}">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="images/quest_no_image.jpg"
                                                         style="max-width: inherit; text-align: center"
                                                         class="img-thumbnail d-block mx-auto "
                                                         alt="${requestScope.question.image}">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-7 mb-2">
                                <div class="h-100">
                                    <textarea class="form-control" id="textarea"
                                              style="height: inherit"
                                              name="text"
                                              placeholder="Введите полный текст вопроса"
                                              required>${requestScope.question.text}</textarea>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-2">
                            <div class="col-md-6">
                                <label class="form-label my-2" for="questionImage">Изображение</label>
                                <input id="questionImage" name="image" type="file"
                                       class="form-control form-control-sm input-md">
                            </div>
                            <div class="col-md-3">
                                <!-- Select Basic -->
                                <label class="form-label my-2" for="questionGameState">Состояние</label>
                                <select id="questionGameState" name="gameState"
                                        class="form-select form-select-sm">
                                    <c:forEach items="${applicationScope.gameStates}" var="gameState">
                                        <option value="${gameState}" ${gameState==requestScope.question.gameState?"selected":""}>
                                            <fmt:message key="game_state.${gameState.name().toLowerCase()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

<%--                            <script type="text/javascript">--%>
<%--                                window.onload=function() {--%>
<%--                                    document.getElementById('questionGameState').onchange=function () {--%>
<%--                                        setValue(this);--%>
<%--                                    };--%>
<%--                                };--%>
<%--                                function setValue(Obj){--%>
<%--                                    document.getElementById('siF4').value=Obj.value;--%>
<%--                                }--%>
<%--                            </script>--%>

<%--                            <input name="csign" size="10" type="text" id="siF4" />--%>


                            <!-- Button -->
                            <div class="col-md-3 align-self-end text-end">
                                <button class="btn btn-outline-secondary" id="update" name="questionUpdate">
                                    Сохранить
                                </button>
                            </div>
                        </div>

                    </form>
                    <hr>

                    <c:choose>
                        <c:when test='${requestScope.question.gameState=="PLAY"}'>
                            <div class="mx-3 align-middle">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="table-responsive">
                                            <table class="table table-sm mb-0" style="font-size: small">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Ответ</th>
                                                    <th scope="col">Следующий вопрос</th>
                                                    <th scope="col"></th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <c:forEach var="entry" items="${requestScope.answers}">

                                                    <tr>
                                                        <td>${entry.key.text}</td>
                                                        <td><a class="link-primary text-decoration-none"
                                                               href="javascript:insertParam('questionIndex', '${requestScope.quest.questions.indexOf(entry.value)}');">
                                                                ${requestScope.quest.questions.indexOf(entry.value)+1} ${entry.value.name}
                                                        </a></td>
                                                        <td><a class="link-primary text-decoration-none"
                                                               href="javascript:postToUrl('/quest-edit?id=${param.id}', {'answerDelete':'${entry.key.id}'});">
                                                            x
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
                                              action="${pageContext.request.contextPath}/quest-edit?id=${param.id}&questionIndex=${param.questionIndex}"
                                              method="post" novalidate>

                                            <label class="form-label" for="answerText">Текст ответа</label>
                                            <input id="answerText" name="answer" type="text"
                                                   placeholder="Введите текст ответа"
                                                   class="form-control form-control-sm input-md mb-1" required>

                                            <select class="form-select form-select-sm" name="nextQuestionId"
                                                    aria-label="" required>
                                                <option selected disabled value="">Выберите вопрос</option>
                                                <c:forEach var="question" items="${requestScope.quest.questions}"
                                                           varStatus="loop">
                                                    <c:if test="${question.id != requestScope.question.id}">
                                                        <option value="${question.id}">${loop.count} ${question.name}</option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>

                                            <div class="d-flex flex-column align-items-end my-3">
                                                <button class="btn btn-outline-secondary" type="submit"
                                                        id="submitAnswer" value="answer" name="answerCreate">
                                                    Добавить ответ
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p class="fw-normal fs-6 text-center mt-3">
                                Для состояния Победа или Проигрыш нельзя добавлять ответы</p>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="components/footer.jsp" %>