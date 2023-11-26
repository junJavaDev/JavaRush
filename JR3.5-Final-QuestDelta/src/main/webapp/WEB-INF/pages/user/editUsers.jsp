<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>


<div class="container-md align-middle">
    <table class="table table-hover table-sm mx-auto w800">
        <thead>
        <tr class="align-middle">
            <th scope="col">${langId}</th>
            <th scope="col">${langAvatar}</th>
            <th scope="col">${langLogin}</th>
            <th scope="col">${langRole}</th>
            <th scope="col">${langProfile}</th>
            <th scope="col">${langLanguage}</th>
            <th scope="col">${langEdit}</th>
            <th scope="col">${langDelete}</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="user" items="${requestScope.users}">
            <tr class="align-middle">
                <th scope="row">${user.id}</th>
                <td><img src="${rootPath}${S.imgDir}${not empty user.avatar ? user.avatar : S.defaultAvatar}" alt="${langAvatar}"
                         class="img-thumbnail h40"></td>
                <td>${user.login}</td>
                <td><fmt:message key="role.${user.role.name().toLowerCase()}"/></td>
                <td><a class="text-decoration-none"
                       href="${rootPath}${Go.PROFILE}?${S.paramId}=${user.id}">${langProfile}</a></td>
                <td>${user.language}</td>
                <td><a class="text-decoration-none"
                       href="${rootPath}${Go.EDIT_USER}?${S.paramId}=${user.id}">
                        ${langEdit}
                </a>
                </td>
                <td>
                    <c:if test="${user.id != sessionScope.user.id}">
                        <a class="text-decoration-none"
                           href="javascript:postToUrl('${rootPath}${Go.USERS}', {'${S.inputDelete}':'${user.id}'});">
                                ${langTableDelete}
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-flex flex-row justify-content-between mx-auto w800">

        <div class="d-flex flex-column">
            <button class="btn btn-outline-secondary" type="button"
                    onclick="document.location='${rootPath}${Go.EDIT_USER}?${S.paramId}=${S.zero}'">
                ${сreateUserBtn}
            </button>
        </div>
        <div class="d-flex flex-column">
            <nav aria-label="page-navigation">
                <ul class="pagination d-flex flex-wrap">
                    <c:forEach var="currentPage" begin="1" end="${requestScope.pageCount}">
                        <c:choose>
                            <c:when test="${currentPage != requestScope.pageNumber}">
                                <li class="page-item">
                                    <a class="page-link" href="javascript:insertParam('${S.paramPageNumber}', '${currentPage}')">
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
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '5');">5</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '10');">10</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '20');">20</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '50');">50</a></li>
                    <li><a class="dropdown-item" href="javascript:insertParam('${S.paramPageSize}', '100');">100</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/components/footer.jsp" %>