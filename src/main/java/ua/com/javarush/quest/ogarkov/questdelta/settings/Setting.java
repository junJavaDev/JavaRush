package ua.com.javarush.quest.ogarkov.questdelta.settings;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@Getter
public class Setting {

    private static final String SETTING_YAML = "setting.yaml";

    //==================================== DATA updateable from Yaml ===============================================
    public final int defaultPageNumber = set(DEFAULT_PAGE_NUMBER);
    public final int defaultPageSize = set(DEFAULT_PAGE_SIZE);
    public final String paramId = set(PARAM_ID);
    public final String paramPageNumber = set(PARAM_PAGE_NUMBER);
    public final String paramPageSize = set(PARAM_PAGE_SIZE);

    public final String paramLang = set(PARAM_LANG);
    public final String paramQuestionIndex = set(PARAM_QUESTION_INDEX);
    public final String paramQuestionDelete = set(PARAM_QUESTION_DELETE);
    public final String paramQuestionCreate = set(PARAM_QUESTION_CREATE);
    public final String paramQuestionUpdate = set(PARAM_QUESTION_UPDATE);
    public final String paramQuestDelete = set(PARAM_QUEST_DELETE);
    public final String paramAnswerCreate = set(PARAM_ANSWER_CREATE);
    public final String paramAnswerDelete = set(PARAM_ANSWER_DELETE);
    public final String paramNextQuestionId = set(PARAM_NEXT_QUESTION_ID);

    public final String inputName = set(INPUT_NAME);
    public final String inputTwine = set(INPUT_TWINE);
    public final String inputContent = set(INPUT_CONTENT);
    public final String inputImage = set(INPUT_IMAGE);
    public final String inputLogin = set(INPUT_LOGIN);
    public final String inputKeyword = set(INPUT_KEYWORD);
    public final String inputPassword = set(INPUT_PASSWORD);
    public final String inputRole = set(INPUT_ROLE);
    public final String inputText = set(INPUT_TEXT);
    public final String inputAnswer = set(INPUT_ANSWER);
    public final String inputNextQuestionId = set(INPUT_NEXT_QUESTION_ID);
    public final String inputGameState = set(INPUT_GAME_STATE);
    public final String inputSignIn = set(INPUT_SIGN_IN);
    public final String inputUpdate = set(INPUT_UPDATE);
    public final String inputCreate = set(INPUT_CREATE);
    public final String inputDelete = set(INPUT_DELETE);
    public final String imageAbout = set(IMAGE_ABOUT);

    public final String bootstrap = set(BOOTSTRAP);
    public final String insertParam = set(INSERT_PARAM);
    public final String postToUrl = set(POST_TO_URL);
    public final String formValidation = set(FORM_VALIDATION);
    public final String defaultAvatar = set(DEFAULT_AVATAR);
    public final String defaultImage = set(DEFAULT_IMAGE);
    public final String defaultLanguage = set(DEFAULT_LANGUAGE);

    public final String questImgPrefix = set(QUEST_IMG_PREFIX);
    public final String helpDir = set(HELP_DIR);
    public final String twineDir = set(TWINE_DIR);
    public final String questsDir = set(QUESTS_DIR);
    public final String jsDir = set(JS_DIR);
    public final String imgDir = set(IMG_DIR);
    public final String usersDir = set(USERS_DIR);
    public final String pagesDir = set(PAGES_DIR);

    public final String attrRoles = set(ATTR_ROLES);
    public final String attrUser = set(ATTR_USER);
    public final String attrUsers = set(ATTR_USERS);
    public final String attrUserId = set(ATTR_USER_ID);
    public final String attrQuest = set(ATTR_QUEST);
    public final String attrQuestion = set(ATTR_QUESTION);
    public final String attrAnswers = set(ATTR_ANSWERS);
    public final String attrOpenQuests = set(ATTR_OPEN_QUESTS);
    public final String attrAuthors = set(ATTR_AUTHORS);
    public final String attrPageSize = set(ATTR_PAGE_SIZE);
    public final String attrPageNumber = set(ATTR_PAGE_NUMBER);
    public final String attrPageCount = set(ATTR_PAGE_COUNT);
    public final String attrQuests = set(ATTR_QUESTS);
    public final String attrLang = set(ATTR_LANG);
    public final String attrWins = set(ATTR_WINS);
    public final String attrLoses = set(ATTR_LOSES);

    public final String attrUsersRegistered = set(ATTR_USERS_REGISTERED);
    public final String attrGamesPlayed = set(ATTR_GAMES_PLAYED);
    public final String attrQuestCreated = set(ATTR_QUEST_CREATED);
    public final String attrQuestionsCreated = set(ATTR_QUESTIONS_CREATED);
    public final String attrAnswersCreated = set(ATTR_ANSWERS_CREATED);
    public final String attrBestPlayerId = set(ATTR_BEST_PLAYER_ID);
    public final String attrWorstPlayerId = set(ATTR_WORST_PLAYER_ID);
    public final String attrMostPopularQuestId = set(ATTR_MOST_POPULAR_QUEST_ID);
    public final String attrBestPlayerLogin = set(ATTR_BEST_PLAYER_LOGIN);
    public final String attrWorstPlayerLogin = set(ATTR_WORST_PLAYER_LOGIN);
    public final String attrMostPopularQuestName = set(ATTR_MOST_POPULAR_QUEST_NAME);
    public final String attrBestPlayerWins = set(ATTR_BEST_PLAYER_WINS);
    public final String attrWorstPlayerLoses = set(ATTR_WORST_PLAYER_LOSES);
    public final String attrMostPopularQuestLaunches = set(ATTR_MOST_POPULAR_QUEST_LAUNCHES);

    public final String jspEditUser = set(JSP_EDIT_USER);
    public final String jspEditUsers = set(JSP_EDIT_USERS);
    public final String jspEditProfile = set(JSP_EDIT_PROFILE);
    public final String jspEditQuestContent = set(JSP_EDIT_QUEST_CONTENT);
    public final String jspEditQuest = set(JSP_EDIT_QUEST);
    public final String jspQuests = set(JSP_QUESTS);
    public final String jspEditQuests = set(JSP_EDIT_QUESTS);
    public final String jspAbout = set(JSP_ABOUT);
    public final String jspHome = set(JSP_HOME);
    public final String jspStatistics = set(JSP_STATISTICS);
    public final String jspPlay = set(JSP_PLAY);
    public final String jspLogin = set(JSP_LOGIN);
    public final String jspProfile = set(JSP_PROFILE);

    public final String zero = set(ZERO);
    public final String[] emptyStringArray = set(EMPTY_STRING_ARRAY);
    public final String deleted = set(DELETED);
    public final String notExist = set(NOT_EXIST);
    public final String notFoundCmd = set(NOT_FOUND_CMD);
    public final String incorrectUri = set(INCORRECT_URI);

    public final int playStartAgainValue = set(PLAY_START_AGAIN_VALUE);
    public final int playCompleteValue = set(PLAY_COMPLETE_VALUE);

    //==================================== /DATA ==============================================

    //============================ SAFE_THREAD_SINGLETON ======================================
    private static volatile Setting SETTING;

    public static Setting get() {
        Setting setting = SETTING;
        if (Objects.isNull(setting)) {
            synchronized (Setting.class) {
                if (Objects.isNull(setting = SETTING)) {
                    setting = SETTING = new Setting();
                }
            }
        }
        return setting;
    }
    //============================ /SAFE_THREAD_SINGLETON ====================================


    //================================ INIT ========================================
    private Setting() {
        updateFromYAML();
    }

    private void updateFromYAML() {
        URL settingResource = Setting.class.getClassLoader().getResource(SETTING_YAML);
        if (Objects.nonNull(settingResource)) {
            try (InputStream inputStream = settingResource.openStream()) {
                Yaml yaml = new Yaml();
                Map<String, Object> parametersMap = yaml.load(inputStream);
                Field[] settingFields = Setting.class.getDeclaredFields();
                for (Field settingField : settingFields) {
                    String name = settingField.getName();
                    Object yamlParameter = parametersMap.get(name);
                    if (yamlParameter != null) {
                        settingField.setAccessible(true);
                        settingField.set(this, parametersMap.get(name));
                        settingField.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static <T> T set(T value) {
        return value;
    }
}
//=============================== /INIT ========================================
