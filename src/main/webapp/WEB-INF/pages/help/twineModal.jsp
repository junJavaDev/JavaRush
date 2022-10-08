<%@ page contentType="text/html; charset=UTF-8" %>
<div class="modal fade" id="twineModal" tabindex="-1" aria-labelledby="twineModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="twineModalLabel">${langTwineTitle}</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ${langTwineAbout}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}1.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineShowPassages}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}2.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineQuestionWithAnswers}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}3.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineNoFormatting}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}4.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineQuestion}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}5.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineWin}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}6.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwineLose}
                <img src="${rootPath}${S.imgDir}${S.helpDir}${S.twineDir}7.png"
                     class="img-thumbnail d-block mx-auto"
                     alt="${langImage}">
                ${langTwinePublishFile}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="window.open('https://twinery.org/2')">
                    ${langOpen} Twine
                </button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">${langClose}</button>
            </div>
        </div>
    </div>
</div>