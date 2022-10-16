package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.Role;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

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
    private static final Logger log = LoggerFactory.getLogger(EditUserServlet.class);

    @Override
    public void init() {
        getServletContext().setAttribute(S.attrRoles, Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Parser.getCommand(req).equals(Go.SIGNUP)) {
            log.info("Open page: {}, userID: {}", Go.SIGNUP, Parser.userId(req));
            Jsp.forward(req, resp, S.jspEditUser);
        } else {
            log.info("Open page: {}, userID: {}", Go.EDIT_USER, Parser.userId(req));
            FormData formData = FormData.of(req);
            long editedId = formData.getId();
            Optional<UserDto> optUser = userService.get(editedId);
            optUser.ifPresent(user -> req.setAttribute(S.attrUser, user));
            Jsp.forward(req, resp, S.jspEditUser);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long id = formData.getId();
        Part data = req.getPart(S.inputImage);
        postUser(formData, data, id);
        Jsp.redirect(req, resp, Go.USERS);
    }

    private void postUser(FormData formData, Part data, long id) throws IOException {
        boolean present = userService.get(id).isPresent();
        if (present && formData.getParameter(S.inputUpdate) != null) {
            userService.update(formData, id);
            userService.uploadAvatar(data, id);
        } else if (present && formData.getParameter(S.inputDelete) != null) {
            userService.delete(id);
        } else if (!present && formData.getParameter(S.inputCreate) != null) {
            id = userService.create(formData);
            userService.uploadAvatar(data, id);
        } else {
            UnsupportedOperationException exception = new UnsupportedOperationException(S.notFoundCmd);
            log.error("Unsupported Operation", exception);
            throw exception;
        }
    }
}
