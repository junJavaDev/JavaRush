<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:message key="footer.copyright" var="footerCopyright"/>
<div class="container">
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <%@ include file="navbarMenuList.jsp" %>
        </ul>
        <p class="text-center text-muted">${footerCopyright}</p>
    </footer>
</div>
<script src="${rootPath}/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/insert.param-1.0.js"></script>
<%@ include file="bodyCloseTags.txt" %>


