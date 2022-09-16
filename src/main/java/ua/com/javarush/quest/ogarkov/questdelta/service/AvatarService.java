package ua.com.javarush.quest.ogarkov.questdelta.service;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

public enum AvatarService {
    INSTANCE;

    private final Path root = Path.of(
            Objects.requireNonNull(AvatarService.class.getResource("/"))
                    .toString()
                    .replace("file:/", "")
                    .concat("../images/")
    );

    @SneakyThrows
    AvatarService() {
        Files.createDirectories(root);
    }

    @SneakyThrows
    public void uploadAvatar(String name, InputStream data) {
        try (data) {
            if (data.available() > 0) {
                Files.copy(data, root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    @SneakyThrows
    public Optional<Path> getAvatarPath(String filename) {
        return Files.exists(root.resolve(filename))
                ? Optional.of(root.resolve(filename))
                : Optional.of(root.resolve("no_image.jpg"));
    }
}
