<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="quests.id_table_header" var="idTableHeader"/>
<fmt:message key="quests.name_table_header" var="nameTableHeader"/>
<fmt:message key="quests.author_table_header" var="authorTableHeader"/>
<fmt:message key="quests.image_table_header" var="imageTableHeader"/>
<fmt:message key="quests.control_table_header" var="controlTableHeader"/>
<fmt:message key="quests.start_new_game_btn" var="startNewGameBtn"/>
<fmt:message key="quests.continue_game_btn" var="continueGameBtn"/>

<div class="container-md my-3">
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="quest" items="${requestScope.quests}">
            <div class="col">
                <div class="card h-100">
                    <c:choose>
                        <c:when test="${quest.image != null}">
                            <img src="images/${quest.image}"
                                 class="card-img-top" style="max-height: 250px"
                                 alt="${quest.image}">
                        </c:when>
                        <c:otherwise>
                            <img src="images/quest_no_image.jpg" class="card-img-top" style="max-height: 250px"
                                 alt="${quest.image}">
                        </c:otherwise>
                    </c:choose>
                    <div class="card-body">
                        <h5 class="card-title">${quest.name}</h5>
                        <p class="card-text">${quest.text}</p>
                    </div>
                    <div class="d-flex flex-column align-items-end mx-3 mb-3">
                        <button class="btn btn-outline-secondary" type="button"
                                onclick="document.location='${rootPath}/play?questId=${quest.id}'">
                                ${requestScope.openQuests.contains(quest.id) ? continueGameBtn : startNewGameBtn}
                        </button>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex flex-row justify-content-between">
                            <div class="flex-column align-self-end">
                                <small class="text-muted">id: <b>${quest.id}</b></small>
                            </div>
                            <div class="flex-column align-self-end">
                                <small class="text-muted">author: <b>${requestScope.userNames.get(quest.id)}</b></small>
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
                    <c:forEach var="currentPage" begin="1" end="${requestScope.pages}">
                        <c:choose>
                            <c:when test="${currentPage != requestScope.activePage}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="javascript:insertParam('pageNumber', '${currentPage}')">
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
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '3');">3</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '9');">9</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '18');">18</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '48');">48</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="components/footer.jsp" %>