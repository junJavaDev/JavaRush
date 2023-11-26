<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">
    <!-- Form Name -->
    <p class="fw-normal fs-4 my-3 mx-auto">
        ${langEditProfileLegend}
    </p>

    <form class="form-horizontal needs-validation" action="${rootPath}${Go.EDIT_PROFILE}" method="post"
          enctype="multipart/form-data" novalidate>

        <fieldset class="w400">

            <div class="row">

                <!-- Password input-->
                <%@ include file="/WEB-INF/pages/user/form-elements/passwordInput.jsp" %>

                <!-- Avatar input-->
                <%@ include file="/WEB-INF/pages/user/form-elements/avatarInput.jsp" %>

                <!-- Buttons -->
                <div class="d-flex justify-content-around my-3">
                    <button id="submit" name="${S.inputUpdate}"
                            class="btn btn-outline-secondary">
                        ${langUpdateBtn}
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>