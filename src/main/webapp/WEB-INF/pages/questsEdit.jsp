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

<div class="container-md">
    <table class="table table-hover ">
        <thead>
        <tr class="align-middle">
            <th scope="col">${idTableHeader}</th>
            <th scope="col">${authorTableHeader}</th>
            <th scope="col">${imageTableHeader}</th>
            <th scope="col">${nameTableHeader}</th>
            <th scope="col">${controlTableHeader}</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="quest" items="${requestScope.quests}">
            <tr class="align-middle">
                <th scope="row">${quest.id}</th>
                <th scope="row">${quest.authorId}</th>
                <td>
                    <c:choose>
                        <c:when test="${quest.image != null}">
                            <img src="images/quests/${quest.id}/${quest.image}" height="40" alt="${quest.image}">
                        </c:when>
                        <c:otherwise>
                            <img src="images/no_image.jpg" height="40" alt="${quest.image}">
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${quest.name}</td>
                <td>
                    <button class="btn btn-outline-secondary" type="button"
                            onclick="document.location='${rootPath}/play?questId=${quest.id}'">
                            ${requestScope.openQuests.contains(quest.id) ? continueGameBtn : startNewGameBtn}
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex flex-row justify-content-between">

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
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '5');">5</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '10');">10</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '20');">20</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '50');">50</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('pageSize', '100');">100</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<%@ include file="components/footer.jsp" %>