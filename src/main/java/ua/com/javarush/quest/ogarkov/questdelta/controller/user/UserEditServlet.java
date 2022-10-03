package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "UserEditServlet", value = {USER, SIGNUP, USER_EDIT})
public class UserEditServlet extends HttpServlet {


    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;


    @Override
    public void init() {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            long userId = getUserId(request);
            Optional<User> optionalUser = userService.get(userId);
            optionalUser.ifPresent(user -> request.setAttribute("user", user));
        Jsp.forward(request, response, "/user/editUser");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = getUserId(req);
        Part data = req.getPart("image");

        User user = User.with()
                .id(id)
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .role(Role.valueOf(req.getParameter("role")))
                .language(Language.EN)
                .build();
        postUser(req, user);

        String avatar = "users/" + user.getId() + ReqParser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(avatar, data.getInputStream());
        if (isUploaded) {
            user.setAvatar(avatar);
        }
        // При обновлении создаётся новый юзер, пока старый лежит в сессии
        // После редактирования своего профиля - перезаписывается сессия
        HttpSession session = req.getSession();
        Optional<Object> currentId = Optional.ofNullable(session.getAttribute("userId"));
        if (currentId.isPresent() && (Long) currentId.get() == id) {
            session.setAttribute("user", user);
        }

        Jsp.redirect(req, resp, USERS);
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
        long id = 0L;
        if (request.getParameter("id") != null) {
            try {
                id = Long.parseLong(request.getParameter("id"));
            } catch (NumberFormatException ignored) {
            }
        }
        return id;
    }
}
