<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="s" scope="application" type="ua.com.javarush.quest.ogarkov.questdelta.settings.Setting"/>
<%--<jsp:useBean id="user" scope="session" type="ua.com.javarush.quest.ogarkov.questdelta.entity.User"/>--%>
<c:set var="rootPath">${pageContext.request.contextPath}</c:set>
<%@ page import="ua.com.javarush.quest.ogarkov.questdelta.entity.Role" %>
<%@ page import="ua.com.javarush.quest.ogarkov.questdelta.entity.Language" %>
<%@ page import="ua.com.javarush.quest.ogarkov.questdelta.entity.GameState" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="lang/language"/>

<%--language variables--%>
<fmt:message key="language.ru" var="langLanguageRU"/>
<fmt:message key="language.en" var="langLanguageEN"/>

<%--Header--%>
<fmt:message key="header.admin_cp" var="langAdminCP"/>
<fmt:message key="header.users_edit" var="langUsersEdit"/>
<fmt:message key="header.quests_edit" var="langQuestsEdit"/>
<fmt:message key="header.user_create" var="langUserCreate"/>
<fmt:message key="header.quest_create" var="langQuestCreate"/>
<fmt:message key="header.logout" var="langLogout"/>
<fmt:message key="header.signup" var="langSignup"/>

<%--Headers--%>
<fmt:message key="headers.language" var="langLanguage"/>
<fmt:message key="headers.id" var="langId"/>
<fmt:message key="headers.login" var="langLogin"/>
<fmt:message key="headers.role" var="langRole"/>
<fmt:message key="headers.avatar" var="langAvatar"/>
<fmt:message key="headers.profile" var="langProfile"/>
<fmt:message key="headers.edit" var="langEdit"/>
<fmt:message key="headers.delete" var="langDelete"/>
<fmt:message key="headers.image" var="langImage"/>
<fmt:message key="headers.author" var="langAuthor"/>
<fmt:message key="headers.questions" var="langQuestions"/>
<fmt:message key="headers.play" var="langPlay"/>


<%--Header Menu--%>
<fmt:message key="navbar_menu.home" var="langMenuHome"/>
<fmt:message key="navbar_menu.quests" var="langMenuQuests"/>
<fmt:message key="navbar_menu.create" var="langMenuCreate"/>
<fmt:message key="navbar_menu.play" var="langMenuPlay"/>
<fmt:message key="navbar_menu.statistic" var="langMenuStatistic"/>
<fmt:message key="navbar_menu.about" var="langMenuAbout"/>

<%--Form legends--%>
<fmt:message key="login.login_form_legend" var="langLoginFormLegend"/>
<fmt:message key="user.edit_profile_legend" var="langEditProfileLegend"/>
<fmt:message key="quest.create_legend" var="langQuestCreateLegend"/>

<%--Quest Name--%>
<fmt:message key="quest.name" var="langQuestName"/>
<fmt:message key="quest.name_placeholder" var="langQuestNamePlaceholder"/>

<%--Quest Text--%>
<fmt:message key="quest.text" var="langQuestText"/>
<fmt:message key="quest.text_placeholder" var="langQuestTextPlaceholder"/>

<%--Quest Image--%>
<fmt:message key="quest.image" var="langQuestImage"/>

<%--User Avatar--%>
<fmt:message key="user.avatar_label" var="langAvatarLabel"/>
<%--Keyword for Admin--%>
<fmt:message key="user.keyword_label" var="langKeywordLabel"/>
<fmt:message key="user.keyword_placeholder" var="langKeywordPlaceholder"/>
<%--User Login--%>
<fmt:message key="login.login_form_legend" var="loginFormLegend"/>
<fmt:message key="login.login_label" var="langLoginLabel"/>
<fmt:message key="login.login_placeholder" var="langLoginPlaceholder"/>
<%--User Password--%>
<fmt:message key="login.password_label" var="langPasswordLabel"/>
<fmt:message key="login.password_placeholder" var="langPasswordPlaceholder"/>
<%--User Role--%>
<fmt:message key="user.role_label" var="roleLabel"/>
<fmt:message key="role.admin" var="roleAdmin"/>
<fmt:message key="role.moderator" var="roleModerator"/>
<fmt:message key="role.user" var="roleUser"/>
<fmt:message key="role.guest" var="roleGuest"/>

<%--Buttons--%>
<fmt:message key="login.sign_in" var="langSignInBtn"/>
<fmt:message key="buttons.update_btn" var="langUpdateBtn"/>
<fmt:message key="buttons.create_btn" var="langCreateBtn"/>
<fmt:message key="buttons.delete_btn" var="langDeleteBtn"/>
<fmt:message key="buttons.save_btn" var="langSaveBtn"/>
<fmt:message key="play.start_again" var="langStartAgainBtn"/>
<fmt:message key="play.complete_quest" var="langCompleteQuestBtn"/>

<%--Profile--%>
<fmt:message key="profile.quests_created" var="langQuestsCreated"/>
<fmt:message key="profile.wins" var="langWins"/>
<fmt:message key="profile.loses" var="langLoses"/>
<fmt:message key="profile.edit_profile_btn" var="langEditProfile"/>
<fmt:message key="profile.edit_user_btn" var="langEditUser"/>

<%--Users table header--%>
<fmt:message key="users.create_user_btn" var="сreateUserBtn"/>
<fmt:message key="users.count_per_page" var="countPerPage"/>

<fmt:message key="user.edit_form_legend" var="langEditFormLegend"/>
<fmt:message key="user.create_form_legend" var="langCreateFormLegend"/>
<fmt:message key="login.login_label" var="langLoginLabel"/>
<fmt:message key="login.login_placeholder" var="langLoginPlaceholder"/>
<fmt:message key="footer.copyright" var="langFooterCopyright"/>
<fmt:message key="title.quest_delta" var="langTitle"/>



<fmt:message key="title.quest_delta" var="langTitle"/>

<%--About--%>
<fmt:message key="about.page_title" var="langAboutPageTitle"/>
<fmt:message key="about.groupIs" var="langAboutGroupIs"/>
<fmt:message key="about.group" var="langAboutGroup"/>
<fmt:message key="about.university_is" var="langAboutUniversityIs"/>
<fmt:message key="about.university" var="langAboutUniversity"/>
<fmt:message key="about.author_is" var="langAboutAuthorIs"/>
<fmt:message key="about.author" var="langAboutAuthor"/>

<%--Statistics--%>
<fmt:message key="statistics.page_title" var="langStatisticsPageTitle"/>
<fmt:message key="statistics.user_registered" var="langUserRegistered"/>
<fmt:message key="statistics.games_played" var="langGamesPlayed"/>
<fmt:message key="statistics.quests_created" var="langQuestsCreated"/>
<fmt:message key="statistics.questions_created" var="langQuestionsCreated"/>
<fmt:message key="statistics.answers_created" var="langAnswersCreated"/>
<fmt:message key="statistics.best_player" var="langBestPlayer"/>
<fmt:message key="statistics.wins" var="langWins"/>
<fmt:message key="statistics.worst_player" var="langWorstPlayer"/>
<fmt:message key="statistics.loses" var="langLoses"/>
<fmt:message key="statistics.most_popular_quest" var="langMostPopularQuest"/>
<fmt:message key="statistics.launches" var="langLaunches"/>

<fmt:message key="home.welcome" var="langWelcome"/>

<%--Editor--%>
<fmt:message key="editor.question_id" var="langQuestionId"/>
<fmt:message key="editor.question_name" var="langQuestionName"/>
<fmt:message key="editor.question_name_placeholder" var="langQuestionNamePlaceholder"/>
<fmt:message key="editor.question_text" var="langQuestionText"/>
<fmt:message key="editor.question_text_placeholder" var="langQuestionTextPlaceholder"/>
<fmt:message key="editor.question_connection" var="langQuestionConnection"/>
<fmt:message key="editor.question_state" var="langQuestionState"/>
<fmt:message key="editor.table_delete" var="langTableDelete"/>
<fmt:message key="editor.quest_delete_btn" var="langQuestDeleteBtn"/>
<fmt:message key="editor.question_add_btn" var="langQuestionAddBtn"/>
<fmt:message key="editor.answer_name" var="langAnswerName"/>
<fmt:message key="editor.answer_text" var="langAnswerText"/>
<fmt:message key="editor.answer_text_placeholder" var="langAnswerTextPlaceholder"/>
<fmt:message key="editor.next_question" var="langAnswerNextQuestion"/>
<fmt:message key="editor.choose_question" var="langChooseQuestion"/>
<fmt:message key="editor.add_answer" var="langAddAnswer"/>
<fmt:message key="editor.cant_add_answer" var="langCantAddAnswer"/>


<fmt:message key="question.create_legend" var="langQuestionCreateLegend"/>

<fmt:message key="quests.start_new_game_btn" var="startNewGameBtn"/>
<fmt:message key="quests.continue_game_btn" var="continueGameBtn"/>

<%--<fmt:message key="quests.name_table_header" var="nameTableHeader"/>--%>
<%--<fmt:message key="quests.author_table_header" var="authorTableHeader"/>--%>
<%--<fmt:message key="headers.image" var="imageTableHeader"/>--%>
<%--<fmt:message key="quests.control_table_header" var="controlTableHeader"/>--%>

<%--<fmt:message key="login.login_label" var="langLoginLabel"/>--%>
<%--<fmt:message key="login.password_label" var="langPasswordLabel"/>--%>
<%--<fmt:message key="login.login_form_legend" var="loginFormLegend"/>--%>

<%--<fmt:message key="login.password_placeholder" var="langPasswordPlaceholder"/>--%>
<%--<fmt:message key="login.login_placeholder" var="langLoginPlaceholder"/>--%>

<%--<fmt:message key="login.sign_in" var="langSignInBtn"/>--%>
<%--<fmt:message key="login.login_label" var="langLoginLabel"/>--%>
<%--<fmt:message key="login.password_label" var="langPasswordLabel"/>--%>
<%--<fmt:message key="login.login_form_legend" var="loginFormLegend"/>--%>

<%--<fmt:message key="login.password_placeholder" var="langPasswordPlaceholder"/>--%>
<%--<fmt:message key="login.login_placeholder" var="langLoginPlaceholder"/>--%>

<%--<fmt:message key="login.sign_in" var="langSignInBtn"/>--%>

<%--<fmt:message key="quests.id_table_header" var="idTableHeader"/>--%>
<%--<fmt:message key="quests.name_table_header" var="nameTableHeader"/>--%>
<%--<fmt:message key="quests.author_table_header" var="authorTableHeader"/>--%>
<%--<fmt:message key="headers.image" var="imageTableHeader"/>--%>
<%--<fmt:message key="quests.control_table_header" var="controlTableHeader"/>--%>
<%--<fmt:message key="quests.start_new_game_btn" var="startNewGameBtn"/>--%>
<%--<fmt:message key="quests.continue_game_btn" var="continueGameBtn"/>--%>
