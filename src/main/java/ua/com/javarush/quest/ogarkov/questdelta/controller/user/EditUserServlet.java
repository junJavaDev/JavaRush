package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet({Go.SIGNUP, Go.EDIT_USER})
public class EditUserServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    public void init() {
        getServletContext()
                .setAttribute(S.attrRoles, Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> optionalUser = ReqParser.getUser(request);
        optionalUser.ifPresent(user -> request.setAttribute(S.attrUser, user));
        Jsp.forward(request, response, S.jspEditUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = ReqParser.getId(req);
        User user = User.with()
                .id(id)
                .login(req.getParameter(S.inputLogin))
                .password(req.getParameter(S.inputPassword))
                .role(Role.valueOf(req.getParameter(S.inputRole)))
                .build();
        postUser(req, user);
        Part data = req.getPart(S.inputImage);
        userService.uploadAvatar(data, user);
        HttpSession session = req.getSession();
        Optional<Object> currentId = Optional.ofNullable(session.getAttribute(S.attrUserId));
        if (currentId.isPresent() && (Long) currentId.get() == id) {
            session.setAttribute(S.attrUser, user);
        }
        Jsp.redirect(req, resp, Go.USERS);
    }

    private void postUser(HttpServletRequest req, User user) {
        boolean present = userService.get(user.getId()).isPresent();
        if (present && req.getParameter(S.inputUpdate) != null) {
            userService.update(user);
        } else if (present && req.getParameter(S.inputDelete) != null) {
            userService.delete(user);
        } else if (!present && req.getParameter(S.inputCreate) != null) {
            userService.create(user);
        } else {
            throw new UnsupportedOperationException(S.notFoundCmd);
        }
    }
}
