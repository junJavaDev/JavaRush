<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>
<fmt:message key="role.admin" var="roleAdmin"/>
<fmt:message key="role.moderator" var="roleModerator"/>
<fmt:message key="role.user" var="roleUser"/>
<fmt:message key="role.guest" var="roleGues"/>

<b>${roleGues}</b> - гость может посмотреть список игр и прочитать о чём сайт, посмотреть статистику <br>
<b>${roleUser}</b> - зарегистрированный пользователь дополнительно может играть в игры, редактировать свой профиль<br>
<b>${roleModerator}</b> - модератор может создавать и редактировать квесты, редактировать пользователей<br>
<b>${roleAdmin}</b> - может добавлять/удалять/редактировать пользователей, квесты, других администраторов, кроме главного админа с id = 1<br>

<%@ include file="components/footer.jsp" %>