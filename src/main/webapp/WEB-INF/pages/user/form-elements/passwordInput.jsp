<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.com.javarush.quest.ogarkov.questdelta.entity.Role" %>

<div class="form-group">
    <label class="control-label my-2" for="userPassword">${langPasswordLabel}</label>
    <input id="userPassword" name="${s.inputPassword}" type="password" placeholder="${langPasswordPlaceholder}"
           class="form-control input-md" required
           value="${sessionScope.role != Role.GUEST && requestScope.user.id > 0
           ? requestScope.user.password
           : ''}">
</div>
