package ua.com.javarush.quest.ogarkov.questdelta.service;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.DEFAULT_AVATAR;

public enum ImageService {
    INSTANCE;

    private final Path root = Path.of(
            Objects.requireNonNull(ImageService.class.getResource("/"))
                    .toString()
                    .replace("file:/", "")
                    .concat("../images/")
    );

    @SneakyThrows
    ImageService() {
        Files.createDirectories(root);
    }

    @SneakyThrows
    public boolean uploadImage(String name, InputStream data) {
        try (data) {
            if (data.available() > 0) {
                Files.createDirectories(root.resolve(name).getParent());
                Files.copy(data, root.resolve(name), StandardCopyOption.REPLACE_EXISTING);
                return true;
            }
            return false;
        }
    }

    @SneakyThrows
    public boolean deleteImage(String name) {
        return Files.deleteIfExists(root.resolve(name));
    }

    @SneakyThrows
    public Optional<Path> getAvatarPath(String filename) {
        Path path = root.resolve(filename);
        return Files.exists(path)
                ? Optional.of(root.resolve(filename))
                : Optional.of(root.resolve(DEFAULT_AVATAR));
    }
}
