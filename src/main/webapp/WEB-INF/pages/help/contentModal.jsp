<%@ page contentType="text/html; charset=UTF-8" %>
<div class="modal fade" id="contentModal" tabindex="-1" aria-labelledby="contentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="contentModalLabel">${langContentModalTitle}</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${langContentAbout}
                ${langContentRules}
                ${langContentMarks}
                ${langContentQuestionNameFormat}
                ${langContentQuestionFormat}
                ${langContentAnswerFormat}
                ${langContentNoAnswers}
                ${langContentExampleTitle}
                <div class="card mx-auto">
                    <div class="card-body">
                        ${langContentExample}
                    </div>
                </div>
                ${langContentExampleAbout}
                ${langContentGoodExampleTitle}
                <div class="card mx-auto">
                    <div class="card-body">
                        ${langContentGoodExample}
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">${langClose}</button>
            </div>
        </div>
    </div>
</div>