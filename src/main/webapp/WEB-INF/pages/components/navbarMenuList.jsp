<fmt:message key="navbar_menu.home" var="navbarMenuHome"/>
<fmt:message key="navbar_menu.quests" var="navbarMenuQuests"/>
<fmt:message key="navbar_menu.create" var="navbarMenuCreate"/>
<fmt:message key="navbar_menu.play" var="navbarMenuPlay"/>
<fmt:message key="navbar_menu.statistic" var="navbarMenuStatistic"/>
<fmt:message key="navbar_menu.about" var="navbarMenuAbout"/>

                <li class="nav-item">
                    <a class="nav-link link-dark" href="${rootPath}/">${navbarMenuHome}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="${rootPath}/quests">${navbarMenuQuests}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="${rootPath}/play?questId=1">${navbarMenuPlay}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="${rootPath}/statistic">${navbarMenuStatistic}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link link-dark" href="${rootPath}/about">${navbarMenuAbout}</a>
                </li>

