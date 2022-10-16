package ua.com.javarush.quest.ogarkov.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.service.ImageService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@WebServlet(Go.IMAGES)
public class ImageServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6551390997304892153L;
    private final ImageService imageService = ImageService.INSTANCE;
    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(ImageServlet.class);

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        int imagesIndex = requestURI.lastIndexOf(S.imgDir);
        String imageName = requestURI.substring(imagesIndex + S.imgDir.length());
        Optional<Path> file = imageService.getAvatarPath(imageName);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            }
        } else {
            log.warn("File {} does not exist", file);
        }
    }
}