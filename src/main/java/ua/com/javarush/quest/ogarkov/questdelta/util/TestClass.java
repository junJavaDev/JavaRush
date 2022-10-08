package ua.com.javarush.quest.ogarkov.questdelta.util;

import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

public class TestClass {

    private static final Setting S = Setting.get();

    public static void main(String[] args) {
        System.out.println(S.defaultLanguage);
    }
}
