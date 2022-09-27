<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="components/header.jsp" %>

<fmt:message key="login.login_label" var="loginLabel"/>
<fmt:message key="login.password_label" var="passwordLabel"/>
<fmt:message key="login.login_form_legend" var="loginFormLegend"/>

<fmt:message key="login.password_placeholder" var="passwordPlaceholder"/>
<fmt:message key="login.login_placeholder" var="loginPlaceholder"/>

<fmt:message key="login.sign_in" var="signIn"/>
<div class="container-md my-3">
    <div class="row">
        <div class="col">
            <div class="card mx-auto my-3 my-custom-scrollbar">
                <div class="table-responsive">
                    <table class="table table-sm mb-0">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Название</th>
                            <th scope="col">🔗</th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="question" items="${requestScope.quest.questions}" varStatus="loop">
                            <tr ${question.id == requestScope.question.id
                                    ?  "class='table-dark'"
                                    :  ""}>
                                <th scope="row"  ${question.gameState == "LOSE"
                                        ?  "class='table-danger'"
                                        : question.gameState == "WIN"
                                        ?  "class='table-success'"
                                        :   ""}>
                                        ${loop.count}
                                </th>
                                <td>${question.name}</td>
                                <td>${question.answers.size()}</td>
                                <td>
                                    <a class="link-primary"
                                       href="javascript:insertParam('questionIndex', '${loop.index}');">✎</a>
                                </td>
                                <td>x</td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <div class="col">
            <div class="card mx-auto my-3 my-custom-scrollbar">

                <div class="table-responsive">
                    <table class="table table-sm mb-0">
                        <thead>
                        <tr>
                            <th scope="col">Ответ</th>
                            <th scope="col">Следующий вопрос</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="entry" items="${requestScope.answers}">

                            <tr>
                                <td>${entry.key.text}</td>
                                <td><a class="link-primary text-decoration-none"
                                       href="javascript:insertParam('questionIndex', '${requestScope.quest.questions.indexOf(entry.value)}');">
                                    [${requestScope.quest.questions.indexOf(entry.value)+1}] ${entry.value.name}
                                </a></td>
                                <td>x</td>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    </div>
</div>


<%@ include file="components/footer.jsp" %>