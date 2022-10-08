package ua.com.javarush.quest.ogarkov.questdelta.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Objects;

import static ua.com.javarush.quest.ogarkov.questdelta.util.TestDefault.*;

public class TestSetting {
    public final String admin = set(ADMIN);
    public final int pages = set(PAGES);
    private static volatile TestSetting SETTING;

    private void updateFromYAML() {
        try {
            Field adminField = TestSetting.class.getDeclaredField("admin");
            Field pagesField = TestSetting.class.getDeclaredField("pages");
            adminField.setAccessible(true);
            adminField.set(this, "YAML: Administrator");
            adminField.setAccessible(false);

            pagesField.setAccessible(true);
            pagesField.set(this, 15);
            pagesField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


//        private void updateFromYAML() {
//            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//            try {
//                URL settingResource = Setting.class.getClassLoader().getResource(SETTING_YAML);
//                Field adminField = Setting.class.getDeclaredField("root");
//                String root1 = mapper.readValue(settingResource, String.class);
//                adminField.setAccessible(true);
//                adminField.set(this, root1);
//                adminField.setAccessible(false);
//            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }

    public static TestSetting get() {
        TestSetting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (TestSetting.class) {
                if (Objects.isNull(setting = SETTING)) {
                    setting = SETTING = new TestSetting();
                }
            }
        }
        return setting;
    }

    private TestSetting() {
        updateFromYAML();
    }

    private <T> T set(T value) {
        return value;
    }



}
