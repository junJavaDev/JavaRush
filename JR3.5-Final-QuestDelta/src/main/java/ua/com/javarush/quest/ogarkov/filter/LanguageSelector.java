package ua.com.javarush.quest.ogarkov.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.Language;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.Optional;

@WebFilter(Go.ALL)
public class LanguageSelector implements Filter {

    private final Setting S = Setting.get();
    UserService userService = UserService.INSTANCE;

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Optional<String> paramLang = Optional.ofNullable(req.getParameter(S.paramLang));
        Optional<Object> sessionUser = Optional.ofNullable(session.getAttribute(S.attrUser));
        Optional<Object> sessionLocale = Optional.ofNullable(session.getAttribute(S.attrLang));

        if (paramLang.isPresent()) {
            Language chosenLanguage = Language.valueOf(paramLang.get());
            if (sessionUser.isPresent()) {
                UserDto user = (UserDto) sessionUser.get();
                user.setLanguage(chosenLanguage);
                userService.changeLang(user);
            }
            session.setAttribute(S.attrLang, chosenLanguage.name());
        } else if (sessionLocale.isEmpty()) {
            session.setAttribute(S.attrLang, sessionUser
                    .map(user -> ((UserDto) user)
                            .getLanguage()
                            .name())
                    .orElse(S.defaultLanguage));
        }
        chain.doFilter(req, resp);
    }
}
