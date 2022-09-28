<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="login.login_label" var="loginLabel"/>
<fmt:message key="login.password_label" var="passwordLabel"/>
<fmt:message key="login.login_form_legend" var="loginFormLegend"/>

<fmt:message key="login.password_placeholder" var="passwordPlaceholder"/>
<fmt:message key="login.login_placeholder" var="loginPlaceholder"/>

<fmt:message key="login.sign_in" var="signIn"/>

<div class="card align-items-center mx-auto my-3" style="width: 500px">
    <p class="fw-normal fs-4 my-3 mx-auto">Новый вопрос</p>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/question-create?id=${param.id}" method="post" enctype="multipart/form-data">
        <fieldset>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label  my-2" for="questionGameState">Состояние квеста</label>
                <div class="col-md-4" style="width: 400px">
                    <select id="questionGameState" name="gameState" class="form-control">
                        <c:forEach items="${applicationScope.gameStates}" var="gameState">
                            <option value="${gameState}" ${gameState=="PLAY"?"selected":""}>
                                <fmt:message key="game_state.${gameState.name().toLowerCase()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>



            <!-- Login input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionName">Название</label>
                <div class="col-md-4" style="width: 400px">
                    <input id="questionName" name="name" type="text" placeholder="Введите название вопроса"
                           class="form-control input-md" required=""
                           value="admin">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionText">Описание</label>
                <div class="col-md-4" style="width: 400px">
                    <input id="questionText" name="text" type="text" placeholder="Введите описание вопроса"
                           class="form-control input-md" required=""
                           value="admin">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionImage">Изображение</label>
                <div class="col-md-4" style="width: 400px">
                    <input id="questionImage" name="image" type="file" class="form-control input-md">
                </div>
            </div>

            <!-- Button -->
            <div class="form-group" style="text-align: center">
                <button class="btn btn-outline-secondary mx-auto my-4" id="submit" name="Sign-in">
                    Создать вопрос
                </button>
            </div>

        </fieldset>
    </form>
</div>
<%@ include file="components/footer.jsp" %>