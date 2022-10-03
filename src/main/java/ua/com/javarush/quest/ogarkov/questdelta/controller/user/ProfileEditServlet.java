package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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
@WebServlet(name = "ProfileEditServlet", value = PROFILE_EDIT)
public class ProfileEditServlet extends HttpServlet {


    @Serial
    private static final long serialVersionUID = 4074368236695365147L;
    private final UserService userService = UserService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(request);
        if (optUser.isPresent()) {
            long id = ReqParser.getId(request, "id");
            User user = optUser.get();
            if (id == user.getId()) {
                User dto = User
                        .with()
                        .id(id)
                        .password(user.getPassword())
                        .build();
                request.setAttribute("user", dto);
            }
        }
        Jsp.forward(request, response, "/user/editProfile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(req);
        if (optUser.isPresent()) {
            Part data = req.getPart("image");
            User user = optUser.get();
            String password = req.getParameter("password");
            if (password != null && !password.isEmpty()) {
                user.setPassword(password);
            }
            String avatar = "avatar-" + user.getId() + ReqParser.getFileExtension(data.getSubmittedFileName());
            boolean isUploaded = imageService.uploadImage(avatar, data.getInputStream());
            if (isUploaded) {
                user.setAvatar(avatar);
            }
            Jsp.redirect(req, resp, PROFILE + "?id=" + user.getId());
        } else Jsp.redirect(req, resp, LOGIN);
    }
}
