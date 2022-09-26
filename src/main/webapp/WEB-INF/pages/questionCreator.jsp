<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="login.login_label" var="loginLabel"/>
<fmt:message key="login.password_label" var="passwordLabel"/>
<fmt:message key="login.login_form_legend" var="loginFormLegend"/>

<fmt:message key="login.password_placeholder" var="passwordPlaceholder"/>
<fmt:message key="login.login_placeholder" var="loginPlaceholder"/>

<fmt:message key="login.sign_in" var="signIn"/>

<div class="card align-items-center mx-auto my-3" style="width: 500px">
    <p class="fw-normal fs-4 my-3 mx-auto">${loginFormLegend}</p>

    <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
        <fieldset>
            <!-- Login input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="userLogin">${loginLabel}</label>
                <div class="col-md-4" style="width: 400px">
                    <input id="userLogin" name="login" type="text" placeholder="${loginPlaceholder}"
                           class="form-control input-md"
                           required=""
                           value="admin">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label my-2" for="userPassword">${passwordLabel}</label>
                <div class="col-md-4" style="width: 400px">
                    <input id="userPassword" name="password" type="password" placeholder="${passwordPlaceholder}"
                           class="form-control input-md" required=""
                           value="admin">

                </div>
            </div>
            <div class="form-group" style="text-align: center">
            <!-- Button -->
            <button class="btn btn-outline-secondary mx-auto my-4" id="submit" name="Sign-in">
                ${signIn}
            </button>
            </div>


        </fieldset>
    </form>
</div>
<%@ include file="components/footer.jsp" %>