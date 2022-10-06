<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="container-md">
    <form class="form-horizontal needs-validation"
          action="${rootPath}${s.editQuest}?${s.paramId}=${param.id}" method="post"
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
                                <input id="questName" name="${s.paramName}" type="text"
                                       placeholder="${langQuestNamePlaceholder}"
                                       class="form-control input-md" required
                                       value="${requestScope.quest.name}">
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questText">Paste the quest content</label>
                                <textarea class="form-control" required style="min-height: 62px"
                                          id="questText"
                                          placeholder="pattern must be..."
                                          name="text">${requestScope.quest.text}</textarea>
                            </div>

                            <!--Image input-->
                            <div class="form-group">
                                <label class="control-label my-2" for="questImage">${langQuestImage}</label>
                                <input id="questImage" name="${s.inputImage}" type="file" class="form-control input-md">
                            </div>

                            <!-- Button -->
                            <div class="form-group text-center">
                                <button class="btn btn-outline-secondary mx-auto my-4" id="submit"
                                        name="${requestScope.quest.id > 0 ? s.updateBtn : s.createBtn}">
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
                        <p class="fw-normal fs-4 my-3 text-center">Add quest content (optional)</p>
                        <div class="col-md-10 mx-auto">
                            <!-- Text area-->
                            <label class="control-label mb-2" for="questPattern">Paste the
                                <a class="text-decoration-none" data-bs-toggle="modal"
                                   data-bs-target="#contentModal" href="#"> quest content</a>
                            </label>
                            <textarea class="form-control" style="min-height: 200px"
                                      id="questPattern"
                                      name="content"
                                      placeholder="pattern must be..."></textarea>

                            <!--File input-->
                            <!-- Button trigger modal -->
                            <div class="form-group">
                                <label class="control-label my-2" for="twineFile">or load
                                    <a class="text-decoration-none" data-bs-toggle="modal"
                                       data-bs-target="#twineModal" href="#"> Twine file</a>
                                </label>
                                <input id="twineFile" name="twine" type="file"
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
<div class="modal fade" id="contentModal" tabindex="-1" aria-labelledby="contentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="contentModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                contentModal
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Twine Modal -->
<div class="modal fade" id="twineModal" tabindex="-1" aria-labelledby="twineModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="twineModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                twineModal
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="window.open('https://twinery.org/2')">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    Open Twine
                </button>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/components/footer.jsp" %>