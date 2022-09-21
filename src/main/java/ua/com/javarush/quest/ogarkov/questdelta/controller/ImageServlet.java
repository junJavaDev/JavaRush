package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;

import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6551390997304892153L;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String nameImage = req.getRequestURI().replace("/images/", "");
        Optional<Path> file = imageService.getAvatarPath(nameImage);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            }
        }
    }
}