package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;
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
@WebServlet(value = Go.EDIT_PROFILE)
public class EditProfileServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(request);
        if (optUser.isPresent()) {
            long id = ReqParser.getLong(request, S.paramId);
            User user = optUser.get();
            if (id == user.getId()) {
                User dto = User
                        .with()
                        .id(id)
                        .password(user.getPassword())
                        .build();
                request.setAttribute(S.attrUser, dto);
            }
        }
        Jsp.forward(request, response, S.jspEditProfile);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(req);
        if (optUser.isPresent()) {
            User user = optUser.get();
            String password = req.getParameter(S.inputPassword);
            if (StringUtils.isNotBlank(password)
                    && StringUtils.isAnyBlank(password)) {
                user.setPassword(password);
            }
            Part data = req.getPart(S.inputImage);
            userService.uploadAvatar(data, user);
            Jsp.redirect(req, resp, Go.PROFILE + "?" + S.paramId + "=" + user.getId());
        } else Jsp.redirect(req, resp, Go.LOGIN);
    }
}
