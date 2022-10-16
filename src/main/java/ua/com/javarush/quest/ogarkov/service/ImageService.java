package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import lombok.SneakyThrows;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Loggable(value = Loggable.DEBUG)
public enum ImageService {
    INSTANCE;
    private final Setting S = Setting.get();

    private final Path root = Path.of(
            Objects.requireNonNull(ImageService.class.getResource("/"))
                    .toString()
                    .replace("file:/", "")
                    .concat("..")
                    .concat(S.imgDir)
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
    public void deleteImage(String name) {
        if (name != null) {
            Path imagePath = root.resolve(name);
            Files.deleteIfExists(imagePath);
        }
    }

    @SneakyThrows
    public Optional<Path> getAvatarPath(String filename) {
        Path path = root.resolve(filename);
        return Files.exists(path)
                ? Optional.of(root.resolve(filename))
                : Optional.of(root.resolve(S.defaultAvatar));
    }
}
