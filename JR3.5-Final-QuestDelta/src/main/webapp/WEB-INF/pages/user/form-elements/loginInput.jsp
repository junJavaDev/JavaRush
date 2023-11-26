<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.com.javarush.quest.ogarkov.entity.Role" %>

<div class="form-group">
    <label class="control-label my-2" for="userLogin">${langLoginLabel}</label>
    <input id="userLogin" name="${S.inputLogin}" type="text" placeholder="${langLoginPlaceholder}"
           class="form-control input-md"  required
           value="${sessionScope.role != Role.GUEST && requestScope.user.id > 0
           ? requestScope.user.login
           : ''}">
</div>
