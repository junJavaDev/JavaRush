package ua.com.javarush.quest.ogarkov.questdelta.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String getFileExtension(String fileName) {
        String extension = "";
        if (fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        return extension;
    }
}
