package ua.com.javarush.quest.ogarkov.questdelta.util;

import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;

@UtilityClass
public class Setting {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String ROOT = "/";
    public static final String USERS = "/users";
    public static final String LOGIN = "/login";
    public static final String SIGNUP = "/signup";
    public static final String PROFILE = "/profile";
    public static final String LOGOUT = "/logout";
    public static final String USER = "/user";
    public static final String ABOUT = "/about";
    public static final String QUESTS = "/quests";
    public static final String PLAY = "/play";
    public static final String STATISTIC = "/statistic";
    public static final String DEFAULT_USER_AVATAR = "no_image.jpg";
    public static final Language DEFAULT_LANGUAGE = Language.EN;
}
