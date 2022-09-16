<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>


<div class="container">
    <form class="form-horizontal" action="user?id=${requestScope.user.id}" method="post" enctype="multipart/form-data">
        <fieldset>

            <!-- Form Name -->
            <legend>User Form</legend>


            <!-- Avatar input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userAvatar">Avatar</label>
                <div class="col-md-4">
                    <input id="userAvatar" name="avatar" type="file" class="form-control input-md">
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md"
                           required=""
                           value="${requestScope.user.login}">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Password</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="pass req"
                           class="form-control input-md" required=""
                           value="${requestScope.user.password}">

                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userRole">Role</label>
                <div class="col-md-4">
                    <select id="userRole" name="role" class="form-control">
                        <c:forEach items="${applicationScope.roles}" var="role">
                            <option value="${role}" ${role==requestScope.user.role?"selected":""}>
                                    ${role}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="${requestScope.user.id>0?"update":"create"}" class="btn btn-success">
                        ${requestScope.user.id>0?"Update":"Create"}
                    </button>
                </div>
            </div>

            <!-- Button -->
            <c:if test="${requestScope.user.id>0}">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="delete"></label>
                    <div class="col-md-4">
                        <button id="delete" name="delete" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </c:if>
        </fieldset>
    </form>
</div>
<%@ include file="components/footer.jsp" %>