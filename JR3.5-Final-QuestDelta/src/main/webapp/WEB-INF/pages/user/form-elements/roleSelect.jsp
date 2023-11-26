<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.com.javarush.quest.ogarkov.entity.Role" %>

<div class="form-group">
    <label class="control-label my-2" for="userRole">${langRole}</label>
    <select id="userRole" name="${S.inputRole}" class="form-control">
        <c:forEach items="${applicationScope.roles}" var="role">
            <c:if test="${role != Role.GUEST}">

                <option value="${role}" ${
                        role == requestScope.user.role
                                || sessionScope.role == Role.GUEST && role==Role.USER
                                ?"selected"
                                :""}>
                    <fmt:message key="role.${role.name().toLowerCase()}"/>
                </option>
            </c:if>
        </c:forEach>
    </select>
</div>
