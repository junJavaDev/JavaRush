<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="WEB-INF/pages/components/header.jsp" %>
<fmt:message key="home.hello" var="homeHello"/>

<%--<script type="text/javascript">--%>
<%--    window.location.href = "${pageContext.request.contextPath}/users";--%>
<%--</script>--%>
<div class="d-flex justify-content-center align-items-center">
<h1>${homeHello}</h1>
</div>
<%@ include file="WEB-INF/pages/components/footer.jsp" %>
