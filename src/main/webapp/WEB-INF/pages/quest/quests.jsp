<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="container-md my-3">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="quest" items="${requestScope.quests}">
            <div class="col">
                <div class="card h-100">
                    <div class="img-box">
                        <img src="${rootPath}${S.imgDir}${not empty quest.image ? quest.image : S.defaultImage}"
                             class="card-img-top quests-image"
                             alt="${langImage}">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${quest.name}</h5>
                        <p class="card-text">${quest.text}</p>
                    </div>
                    <div class="d-flex justify-content-between mx-3 mb-3">
                        <c:if test="${sessionScope.role == Role.ADMIN || sessionScope.user.id == quest.authorId}">
                            <button class="btn btn-outline-secondary" type="button"
                                    onclick="document.location='${rootPath}${Go.EDIT_QUEST_CONTENT}?${S.paramId}=${quest.id}'">
                                    ${langEditorBtn}
                            </button>
                        </c:if>
                        <button class="btn btn-outline-secondary" type="button"
                                onclick="document.location='${rootPath}${Go.PLAY}?${S.paramId}=${quest.id}'">
                                ${requestScope.openQuests.contains(quest.id) ? langContinueGameBtn : langStartNewGameBtn}
                        </button>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex flex-row justify-content-between">
                            <div class="flex-column align-self-end">
                                <small class="text-muted">${langId} <b>${quest.id}</b></small>
                            </div>
                            <div class="flex-column align-self-end">
                                <small class="text-muted">${langAuthor}
                                    <b>${requestScope.authors.get(quest.id)}</b></small>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>

    <div class="d-flex flex-row justify-content-between my-5">

        <div class="d-flex flex-column">
            <%--left side--%>
        </div>
        <div class="d-flex flex-column">
            <nav aria-label="page-navigation">
                <ul class="pagination d-flex flex-wrap">
                    <c:forEach var="currentPage" begin="1" end="${requestScope.pageCount}">
                        <c:choose>
                            <c:when test="${currentPage != requestScope.pageNumber}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="javascript:insertParam('${S.paramPageNumber}', '${currentPage}')">
                                            ${currentPage}
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item active">
                                    <span class="page-link">
                                            ${currentPage}
                                    </span>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </nav>
        </div>

        <div class="d-flex flex-column">
            <div class="dropdown ">
                <button class="btn btn-outline-secondary dropdown-toggle" type="button" id="dropdownMenuButton2"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    ${requestScope.countPerPage} ${requestScope.pageSize}
                </button>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '3');">3</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '9');">9</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '18');">18</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '48');">48</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/components/footer.jsp" %>