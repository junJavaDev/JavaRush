package ua.com.javarush.quest.ogarkov.questdelta.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Locale;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.Optional;

@WebFilter(value = "/*")
public class LocaleSelector implements Filter {

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Optional<Object> user = Optional.ofNullable(httpSession.getAttribute("user"));
        Optional<String> localeParam = Optional.ofNullable(request.getParameter("locale"));
        Optional<Object> sessionLocale = Optional.ofNullable(httpSession.getAttribute("locale"));

        if (localeParam.isPresent()) {
            Locale chosenLocale = Locale.valueOf(localeParam.get());
            user.ifPresent(opUser -> ((User) opUser).setLocale(chosenLocale));
            httpSession.setAttribute("locale", chosenLocale);
        } else if (sessionLocale.isEmpty()) {
            httpSession.setAttribute("locale", user.isPresent()
                    ? ((User) user.get()).getLocale()
                    : Locale.EN);
        }
        chain.doFilter(request, response);
    }
}
