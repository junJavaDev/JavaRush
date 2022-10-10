<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="container-md">
    <form class="form-horizontal needs-validation"
          action="${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${param.id}" method="post"
          enctype="multipart/form-data" novalidate>
        <div class="row justify-content-md-center">

            <div class="col col-md-4">
                <div class="card align-items-center mx-auto my-3 quest-creator">
                    <div class="my-custom-scrollbar mb-0 w-100">
                        <div class="col-md-9 mx-auto">

                            <!-- Form Name -->
                            <p class="fw-normal fs-4 my-3 mx-auto">
                                ${requestScope.quest.id > 0
                                        ? "Edit quest"
                                        : langQuestCreateLegend}
                            </p>

                            <!-- Name input-->
                            <div class="form-group">
                                <label class="control-label mb-2" for="questName">${langQuestName}</label>
                                <input id="questName" name="${S.inputName}" type="text"
                                       placeholder="${langQuestNamePlaceholder}"
                                       class="form-control input-md" required
                                       value="${requestScope.quest.name}">
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questText">${langQuestText}</label>
                                <textarea class="form-control" required style="min-height: 62px"
                                          id="questText"
                                          placeholder="${langQuestTextPlaceholder}"
                                          name="text">${requestScope.quest.text}</textarea>
                            </div>

                            <!--Image input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questImage">${langQuestImage}</label>
                                <input id="questImage" name="${S.inputImage}" type="file" class="form-control input-md">
                            </div>

                            <!-- Button -->
                            <div class="form-group text-center">
                                <button class="btn btn-outline-secondary mx-auto my-4" id="submit"
                                        name="${requestScope.quest.id > 0 ? S.inputUpdate : S.inputCreate}">
                                    ${requestScope.quest.id > 0 ? langUpdateBtn : langCreateBtn}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col col-md-5">
                <div class="card my-3 quest-creator">
                    <div class="my-custom-scrollbar mb-0 w-100">
                        <p class="fw-normal fs-4 my-3 text-center">${langContentTitle}</p>
                        <div class="col-md-10 mx-auto">
                            <!-- Text area-->
                            <label class="control-label mb-2" for="questPattern">${langContentPastThe}
                                <a class="text-decoration-none" data-bs-toggle="modal"
                                   data-bs-target="#contentModal" href="#">${langContentQuestContent}</a>
                            </label>
                            <textarea class="form-control" style="min-height: 200px"
                                      id="questPattern"
                                      name="${S.inputContent}"
                                      placeholder="${langContentQuestContentPlaceholder}"></textarea>

                            <!--File input-->
                            <!-- Button trigger modal -->
                            <div class="form-group">
                                <label class="control-label my-2" for="twineFile">${langContentOrLoad}
                                    <a class="text-decoration-none" data-bs-toggle="modal"
                                       data-bs-target="#twineModal" href="#">${langContentTwineFile}</a>
                                </label>
                                <input id="twineFile" name="${S.inputTwine}" type="file"
                                       class="form-control input-md mb-3">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<!-- Content Modal-->
<%@ include file="/WEB-INF/pages/help/contentModal.jsp" %>

<!-- Twine Modal -->
<%@ include file="/WEB-INF/pages/help/twineModal.jsp" %>


<%@ include file="/WEB-INF/pages/components/footer.jsp" %>