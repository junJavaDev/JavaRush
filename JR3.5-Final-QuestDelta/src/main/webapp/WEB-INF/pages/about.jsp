<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w500">

    <p class="fw-normal fs-4 my-3">${langAboutPageTitle}</p>

    <img src="${rootPath}${S.imgDir}${S.imageAbout}"
         class="img-thumbnail rounded d-block mx-auto mb-3 w400"
         alt="pacific">

    <table class="table mb-4 w400">
        <tr>
            <td class="text-end w200">${langAboutGroupIs}</td>
            <td class="text-start">${langAboutGroup}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langAboutUniversityIs}</td>
            <td class="text-start">${langAboutUniversity}</td>
        </tr>
        <tr>
            <td class="text-end w200">${langAboutAuthorIs}</td>
            <td class="text-start">${langAboutAuthor}</td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/pages/components/footer.jsp" %>