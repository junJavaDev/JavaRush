package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(value = Go.EDIT_PROFILE)
public class EditProfileServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long currentUserId = Parser.userId(req);
        Optional<UserDto> optUser = userService.get(currentUserId);
        if (optUser.isPresent()) {
            UserDto user = optUser.get();
            long editedUserId = formData.getId();
            if (editedUserId == currentUserId) {
                req.setAttribute(S.attrUser, user);
            }
        }
        Jsp.forward(req, resp, S.jspEditProfile);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long id = Parser.userId(req);
        Optional<UserDto> optUser = userService.get(id);
        if (optUser.isPresent()) {
            userService.update(formData, id);
            Part data = req.getPart(S.inputImage);
            userService.uploadAvatar(data, id);
            Jsp.redirect(req, resp, Go.PROFILE + "?" + S.paramId + "=" + id);
        } else Jsp.redirect(req, resp, Go.LOGIN);
    }
}
