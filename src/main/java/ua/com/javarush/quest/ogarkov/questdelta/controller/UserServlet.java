package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.AvatarService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {


    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final AvatarService avatarService = AvatarService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = getUserId(request);
        Optional<User> optionalUser = userService.get(userId);
        optionalUser.ifPresent(user -> request.setAttribute("user", user));
        Jsp.forward(request, response, "user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = getUserId(req);
        Part data = req.getPart("image");

        String avatar = "avatar-" + System.currentTimeMillis();
        avatarService.uploadAvatar(avatar, data.getInputStream());
        User user = User.with()
                .id(id)
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .avatar(avatar)
                .role(Role.valueOf(req.getParameter("role")))
                .build();
        postUser(req, user);
        Jsp.redirect(resp, "users");
    }

    private void postUser(HttpServletRequest req, User user) {
        boolean present = userService.get(user.getId()).isPresent();
        if (present && req.getParameter("update") != null) {
            userService.update(user);
        } else if (present && req.getParameter("delete") != null) {
            userService.delete(user);
        } else if (!present && req.getParameter("create") != null) {
            userService.create(user);
        } else {
            throw new UnsupportedOperationException("not found cmd");
        }
    }

    private long getUserId(HttpServletRequest request) {
        return request.getParameter("id") != null
                ? Long.parseLong(request.getParameter("id"))
                : 0L;
    }
}
