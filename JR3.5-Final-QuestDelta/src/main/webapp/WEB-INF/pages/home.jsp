<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/components/header.jsp" %>

<div class="card align-items-center mx-auto my-3 w600">
        <p class="fw-normal fs-4 text-center mt-3">${langWelcome},
            ${sessionScope.user == null ? roleGuest : sessionScope.user.login}</p>

    <div class="card-body w500">
        <p class="card-text text-start">Ты находишься на главной странице сайта текстовых квестов.</p>
        <p class="card-text text-start">Возможности навигационного меню: </p>
        <p class="card-text text-start">
            <b>${langMenuQuests}</b> - Просмотр списка квестов<br>
            <b>${langMenuPlay}</b> - Запуск последнего квеста<br>
            <b>${langMenuStatistic}</b> - Просмотр статистики<br>
            <b>${langLanguage}</b> - Выбор языка интерфейса</p>
        <p class="card-text">Для запуска или создания квеста нужна регистрация.</p>
        <p class="card-text text-start"><b>${roleGuest}</b> - может просматривать список квестов, ознакомиться с
            сайтом, посмотреть статистику</p>
        <p class="card-text text-start"><b>${roleUser}</b> - зарегистрированный пользователь может проходить квесты
            и создавать свои, редактировать свой профиль и свои квесты</p>
        <p class="card-text text-start mb-3"><b>${roleAdmin}</b> - может добавлять, удалять, редактировать
            пользователей и квесты с помощью дополнительного меню администратора</p>
    </div>
</div>

<%@ include file="/WEB-INF/pages/components/footer.jsp" %>
