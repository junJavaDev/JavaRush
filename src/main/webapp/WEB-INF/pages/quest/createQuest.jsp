<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">
    <p class="fw-normal fs-4 my-3 mx-auto">${langQuestCreateLegend}</p>

    <form class="form-horizontal needs-validation" action="${pageContext.request.contextPath}${s.questCreate}" method="post"
          enctype="multipart/form-data" novalidate>
        <fieldset>
            <!-- Login input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questName">${langQuestName}</label>
                <div class="col-md-4 w400">
                    <input id="questName" name="${s.paramName}" type="text" placeholder="${langQuestNamePlaceholder}"
                           class="form-control input-md" required>
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questText">${langQuestText}</label>
                <div class="col-md-4 w400">
                    <input id="questText" name="text" type="text" placeholder="${langQuestTextPlaceholder}"
                           class="form-control input-md" required>
                </div>
            </div>

            <!--Image input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="questImage">${langQuestImage}</label>
                <div class="col-md-4 w400">
                    <input id="questImage" name="${s.inputImage}" type="file" class="form-control input-md">
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
<%@ include file="../components/footer.jsp" %>