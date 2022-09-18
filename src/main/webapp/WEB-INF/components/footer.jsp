<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container">
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
            <li><a href="#" class="nav-link px-2 link-dark">${admin_cp}</a></li>
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">${home}</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">${quests}</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">${create}</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">${play}</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">${statistic}</a></li>
            <li><a href="#" class="nav-link px-2 link-dark">${about}</a></li>
        </ul>
        <p class="text-center text-muted">© 2022 Company, Inc</p>
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/insert.param-1.0.js"></script>
</body>
</html>


