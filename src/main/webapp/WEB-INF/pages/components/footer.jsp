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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/insert.param-1.0.js"></script>
<%@ include file="bodyCloseTags.txt" %>


