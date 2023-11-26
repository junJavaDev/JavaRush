<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>


<div class="container-md">
    <table class="table table-hover table-sm mx-auto w800">
        <thead>
        <tr class="align-middle">
            <th scope="col">${langId}</th>
            <th scope="col">${langImage}</th>
            <th scope="col">${langQuestName}</th>
            <th scope="col">${langAuthor}</th>
            <th scope="col">${langQuestions}</th>
            <th scope="col">${langPlay}</th>
            <th scope="col">${langEdit}</th>
            <th scope="col">${langDelete}</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="quest" items="${requestScope.quests}">
            <tr class="align-middle">
                <th scope="row">${quest.id}</th>
                <td>
                    <img src="${rootPath}${S.imgDir}${not empty quest.image ? quest.image : S.defaultImage}" height="40"
                         alt="${langImage}">
                </td>
                <td>${quest.name}
                    <a class="text-decoration-none fw-bold"
                       href="${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${quest.id}">
                            ${langPencilBtn}
                    </a>
                </td>
                <td>
                    <a class="text-decoration-nonetext-decoration-none"
                       href="${rootPath}${Go.PROFILE}?${S.paramId}=${quest.authorId}">
                            ${requestScope.authors.get(quest.id)}
                    </a>
                </td>
                <td>
                        ${quest.questions.size()}
                </td>
                <td>
                    <a class="text-decoration-none"
                       href="${rootPath}${Go.PLAY}?${S.paramId}=${quest.id}">
                            ${langPlay}
                    </a>
                </td>
                <td>
                    <a class="text-decoration-none"
                       href="${rootPath}${Go.EDIT_QUEST_CONTENT}?${S.paramId}=${quest.id}">
                            ${langEdit}
                    </a>
                </td>
                <td>
                    <a class="text-decoration-none"
                       href="javascript:postToUrl('${rootPath}${Go.EDIT_QUEST_CONTENT}?${S.paramId}=${quest.id}', {'${S.paramQuestDelete}':'${quest.id}'});">
                            ${langTableDelete}
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex flex-row justify-content-between mx-auto w800">

        <div class="d-flex flex-column">
            <button class="btn btn-outline-secondary" type="button"
                    onclick="document.location='${rootPath}${Go.EDIT_QUEST}?${S.paramId}=${S.zero}'">
                ${langQuestCreateLegend}
            </button>
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
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '5');">5</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '10');">10</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '20');">20</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '50');">50</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '100');">100</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>