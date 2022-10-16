<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.com.javarush.quest.ogarkov.entity.Role" %>


<div class="form-group">
    <label class="control-label my-2" for="userPassword">${langPasswordLabel}</label>
    <input id="userPassword" name="${S.inputPassword}" type="password" placeholder="${langPasswordPlaceholder}"
           class="form-control input-md" ${sessionScope.role == Role.GUEST ? "required" : ""}>
</div>
