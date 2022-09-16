<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<div class="container-md">
    <table class="table table-hover ">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Avatar</th>
            <th scope="col">Login</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="user" items="${requestScope.users}">
                <tr class="align-middle">
                    <th scope="row">${user.id}</th>
                    <td><img src="images/${user.avatar}" height="40" alt="${user.avatar}"></td>
                    <td><a href="user?id=${user.id}">${user.login}</a></td>
                    <td>${user.role}</td>
                </tr>
        </c:forEach>
        </tbody>
    </table>

<div class="d-grid gap-2 col-6 mx-auto">
    <button class="btn btn-success" type="button" onclick="document.location='${pageContext.request.contextPath}/signup'">Create new user</button>
</div>
</div>

<%@ include file="components/footer.jsp" %>