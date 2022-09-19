<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="user.edit_form_legend" var="editFormLegend"/>
<fmt:message key="user.create_form_legend" var="createFormLegend"/>
<fmt:message key="user.avatar_label" var="avatarLabel"/>
<fmt:message key="user.login_label" var="loginLabel"/>
<fmt:message key="user.password_label" var="passwordLabel"/>
<fmt:message key="user.role_label" var="roleLabel"/>
<fmt:message key="user.update_btn" var="updateBtn"/>
<fmt:message key="user.create_btn" var="createBtn"/>
<fmt:message key="user.delete_btn" var="deleteBtn"/>

<div class="container">
    <form class="form-horizontal" action="${requestScope['javax.servlet.forward.request_uri']}" method="post" enctype="multipart/form-data">
        <fieldset>
            <!-- Form Name -->
            <c:choose>
                <c:when test="${requestScope.user.id>0}">
                    <legend>${editFormLegend} <b>${requestScope.user.login}</b></legend>
                </c:when>
                <c:otherwise>
                    <legend>${createFormLegend}</legend>
                </c:otherwise>
            </c:choose>

            <!-- Avatar input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userAvatar">${avatarLabel}</label>
                <div class="col-md-4">
                    <input id="userAvatar" name="avatar" type="file" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">${loginLabel}</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md"
                           required=""
                           value="${requestScope.user.login}">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">${passwordLabel}</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="pass req"
                           class="form-control input-md" required=""
                           value="${requestScope.user.password}">

                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userRole">${roleLabel}</label>
                <div class="col-md-4">
                    <select id="userRole" name="role" class="form-control">
                        <c:forEach items="${applicationScope.roles}" var="role">
                            <option value="${role}" ${role==requestScope.user.role?"selected":""}>
                                <fmt:message key="role.${role.name().toLowerCase()}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <div class="col-md-4">
                    <button id="submit" name="${requestScope.user.id>0?"update":"create"}" class="btn btn-success">
                        <c:choose>
                            <c:when test="${requestScope.user.id>0}">
                                ${updateBtn}
                            </c:when>
                            <c:otherwise>
                                ${createBtn}
                            </c:otherwise>
                        </c:choose>
                    </button>
                </div>
            </div>

            <!-- Button -->
            <c:if test="${requestScope.user.id>0}">
                <div class="form-group">
                    <div class="col-md-4">
                        <button id="delete" name="delete" class="btn btn-danger">${deleteBtn}</button>
                    </div>
                </div>
            </c:if>
        </fieldset>
    </form>
</div>
<%@ include file="components/footer.jsp" %>