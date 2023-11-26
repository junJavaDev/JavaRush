package ua.com.javarush.quest.ogarkov.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.Role;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebFilter({Go.USERS, Go.SIGNUP, Go.PROFILE, Go.LOGIN, Go.PLAY, Go.EDIT_PROFILE, Go.EDIT_USER, Go.LOGOUT, Go.USERS, Go.EDIT_QUEST, Go.EDIT_QUEST_CONTENT, Go.EDIT_QUESTS})
public class RoleSelector implements Filter {

    private static final Setting S = Setting.get();


    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(Go.LOGIN, Go.SIGNUP, Go.EDIT_USER),
            Role.USER, List.of(Go.PROFILE, Go.LOGOUT, Go.EDIT_PROFILE, Go.PLAY, Go.EDIT_QUEST, Go.EDIT_QUEST_CONTENT),
            Role.ADMIN, List.of(Go.USERS, Go.PROFILE, Go.LOGOUT, Go.EDIT_USER, Go.EDIT_PROFILE, Go.EDIT_QUEST, Go.EDIT_QUEST_CONTENT, Go.PLAY, Go.EDIT_QUESTS)
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = Jsp.getCommand(request);
        HttpSession session = request.getSession();
        Object user = session.getAttribute(S.attrUser);

        Role role = (Objects.isNull(user))
                ? Role.GUEST
                : ((UserDto) user).getRole();

        session.setAttribute(S.attrRole, role.name());
        if (uriMap.get(role).contains(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Jsp.redirect(request, response, Go.LOGIN);
        }
    }
}
