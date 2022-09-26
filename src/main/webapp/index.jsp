<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="WEB-INF/pages/components/header.jsp" %>
<fmt:message key="home.hello" var="homeHello"/>
<fmt:message key="role.admin" var="roleAdmin"/>
<fmt:message key="role.moderator" var="roleModerator"/>
<fmt:message key="role.user" var="roleUser"/>
<fmt:message key="role.guest" var="roleGuest"/>

<div class="card align-items-center mx-auto my-3" style="width: 600px">
        <p class="fw-normal fs-4 text-center mt-3">Добро пожаловать,
            ${sessionScope.user == null ? roleGuest : sessionScope.user.login}</p>

    <img class="img-thumbnail quest-image mx-auto" style="width: 400px"
         src="${rootPath}/images/welcome.jpg" alt="wtf">

    <div class="card-body" style="width: 500px">
        <p class="card-text text-start">Ты находишься на главной странице сайта текстовых квестов.</p>
        <p class="card-text text-start">Возможности навигационного меню: </p>
        <p class="card-text text-start">
            <b>${navbarMenuQuests}</b> - Просмотр списка квестов<br>
            <b>${navbarMenuPlay}</b> - Запуск последнего квеста<br>
            <b>${navbarMenuStatistic}</b> - Просмотр статистики<br>
            <b>${headerLanguage}</b> - Выбор языка интерфейса</p>
        <p class="card-text">Для запуска или создания квеста нужна регистрация.</p>
        <p class="card-text text-start"><b>${roleGuest}</b> - может просматривать список квестов, ознакомиться с
            сайтом, посмотреть статистику</p>
        <p class="card-text text-start"><b>${roleUser}</b> - зарегистрированный пользователь может проходить квесты
            и создавать свои, редактировать свой профиль и свои квесты</p>
        <p class="card-text text-start mb-3"><b>${roleAdmin}</b> - может добавлять, удалять, редактировать
            пользователей и квесты с помощью дополнительного меню администратора</p>
    </div>
</div>

<%@ include file="WEB-INF/pages/components/footer.jsp" %>
