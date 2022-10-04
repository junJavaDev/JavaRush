<%@ page contentType="text/html; charset=UTF-8" %>
<div class="container">
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <%@ include file="/WEB-INF/pages/components/navbarMenuList.jsp" %>
        </ul>
        <p class="text-center text-muted">${langFooterCopyright}</p>
    </footer>
</div>
<script crossorigin="anonymous" src="${rootPath}${s.jsDir}${s.bootstrap}"></script>
<script type="text/javascript" src="${rootPath}${s.jsDir}${s.insertParam}"></script>
<script type="text/javascript" src="${rootPath}${s.jsDir}${s.postToUrl}"></script>
<script type="text/javascript" src="${rootPath}${s.jsDir}${s.formValidation}"></script>
<%@ include file="/WEB-INF/pages/components/bodyCloseTags.txt" %>


