<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="users.id_table_header" var="idTableHeader"/>
<fmt:message key="users.avatar_table_header" var="avatarTableHeader"/>
<fmt:message key="users.login_table_header" var="loginTableHeader"/>
<fmt:message key="users.role_table_header" var="roleTableHeader"/>
<fmt:message key="users.create_user_btn" var="сreateUserBtn"/>
<fmt:message key="users.count_per_page" var="countPerPage"/>

<div class="container-md">
    <table class="table table-hover ">
        <thead>
        <tr class="align-middle">
            <th scope="col">${idTableHeader}</th>
            <th scope="col">${avatarTableHeader}</th>
            <th scope="col">${loginTableHeader}</th>
            <th scope="col">${roleTableHeader}</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="user" items="${requestScope.users}">
            <tr class="align-middle">
                <th scope="row">${user.id}</th>
                <td><img src="images/${user.avatar}" height="40" alt="${user.avatar}"></td>
                <td><a href="user?id=${user.id}">${user.login}</a></td>
                <td><fmt:message key="role.${user.role.name().toLowerCase()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex flex-row justify-content-between">

        <div class="d-flex flex-column">
            <button class="btn btn-outline-secondary" type="button"
                    onclick="document.location='${rootPath}/user?id=0'">
               ${сreateUserBtn}
            </button>
        </div>
        <div class="d-flex flex-column">
            <nav aria-label="page-navigation">
                <ul class="pagination d-flex flex-wrap">
                    <c:forEach var="currentPage" begin="1" end="${requestScope.pages}">
                        <c:choose>
                            <c:when test="${currentPage != requestScope.activePage}">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:insertParam('pageNumber', '${currentPage}')">
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
                    ${countPerPage} ${requestScope.pageSize}
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