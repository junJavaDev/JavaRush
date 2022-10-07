<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>


<div class="card align-items-center mx-auto my-3 w500">
    <p class="fw-normal fs-4 my-3 mx-auto">${langQuestionCreateLegend}</p>

    <form class="form-horizontal needs-validation" action="${rootPath}${s.createQuestion}?${s.paramId}=${param.id}"
          method="post" enctype="multipart/form-data" novalidate>
        <fieldset>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label  my-2" for="questionGameState">${langQuestionState}</label>
                <div class="col-md-4 w400">
                    <select id="questionGameState" name="${s.inputGameState}" class="form-control">
                        <c:forEach items="${applicationScope.gameStates}" var="gameState">
                            <option value="${gameState}" ${gameState==GameState.PLAY?"selected":""}>
                                <fmt:message key="game_state.${gameState.name().toLowerCase()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <!-- Name input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionName">${langQuestionName}</label>
                <div class="col-md-4 w400">
                    <input id="questionName" name="${s.paramName}" type="text"
                           placeholder="${langQuestionNamePlaceholder}"
                           class="form-control input-md" required>

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionText">${langQuestionText}</label>
                <div class="col-md-4 w400">
                    <input id="questionText" name="text" type="text" placeholder="${langQuestionTextPlaceholder}"
                           class="form-control input-md" required>
                </div>
            </div>

            <!-- Image input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questionImage">${langImage}</label>
                <div class="col-md-4 w400">
                    <input id="questionImage" name="${s.inputImage}" type="file" class="form-control input-md">
                </div>
            </div>

            <!-- Button -->
            <div class="form-group text-center">
                <button class="btn btn-outline-secondary mx-auto my-4" id="submit" name="${s.createBtn}">
                    ${langCreateBtn}
                </button>
            </div>
        </fieldset>
    </form>
</div>
<%@ include file="/WEB-INF/pages/components/footer.jsp" %>