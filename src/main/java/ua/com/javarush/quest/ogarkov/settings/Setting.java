package ua.com.javarush.quest.ogarkov.settings;

import lombok.Getter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

@Getter
public class Setting {

    private static final String SETTING_YAML = "setting.yaml";
    //============================ SAFE_THREAD_SINGLETON ======================================
    private static volatile Setting SETTING;
    //==================================== DATA updateable from Yaml ===============================================
    public final int defaultPageNumber = set(Default.DEFAULT_PAGE_NUMBER);
    public final int defaultPageSize = set(Default.DEFAULT_PAGE_SIZE);
    public final String paramId = set(Default.PARAM_ID);
    public final String paramPageNumber = set(Default.PARAM_PAGE_NUMBER);
    public final String paramPageSize = set(Default.PARAM_PAGE_SIZE);
    public final String paramLang = set(Default.PARAM_LANG);
    public final String paramQuestionId = set(Default.PARAM_QUESTION_ID);
    public final String paramQuestionDelete = set(Default.PARAM_QUESTION_DELETE);
    public final String paramQuestionCreate = set(Default.PARAM_QUESTION_CREATE);
    public final String paramQuestionUpdate = set(Default.PARAM_QUESTION_UPDATE);
    public final String paramQuestDelete = set(Default.PARAM_QUEST_DELETE);
    public final String paramAnswerCreate = set(Default.PARAM_ANSWER_CREATE);
    public final String paramAnswerDelete = set(Default.PARAM_ANSWER_DELETE);
    public final String inputName = set(Default.INPUT_NAME);
    public final String inputTwine = set(Default.INPUT_TWINE);
    public final String inputContent = set(Default.INPUT_CONTENT);
    public final String inputImage = set(Default.INPUT_IMAGE);
    public final String inputLogin = set(Default.INPUT_LOGIN);
    public final String inputKeyword = set(Default.INPUT_KEYWORD);
    public final String inputPassword = set(Default.INPUT_PASSWORD);
    public final String inputRole = set(Default.INPUT_ROLE);
    public final String inputText = set(Default.INPUT_TEXT);
    public final String inputAnswer = set(Default.INPUT_ANSWER);
    public final String inputNextQuestionId = set(Default.INPUT_NEXT_QUESTION_ID);
    public final String inputGameState = set(Default.INPUT_GAME_STATE);
    public final String inputSignIn = set(Default.INPUT_SIGN_IN);
    public final String inputUpdate = set(Default.INPUT_UPDATE);
    public final String inputCreate = set(Default.INPUT_CREATE);
    public final String inputDelete = set(Default.INPUT_DELETE);
    public final String imageAbout = set(Default.IMAGE_ABOUT);
    public final String bootstrap = set(Default.BOOTSTRAP);
    public final String insertParam = set(Default.INSERT_PARAM);
    public final String postToUrl = set(Default.POST_TO_URL);
    public final String formValidation = set(Default.FORM_VALIDATION);
    public final String defaultAvatar = set(Default.DEFAULT_AVATAR);
    public final String defaultImage = set(Default.DEFAULT_IMAGE);
    public final String defaultLanguage = set(Default.DEFAULT_LANGUAGE);
    public final String questImgPrefix = set(Default.QUEST_IMG_PREFIX);
    public final String helpDir = set(Default.HELP_DIR);
    public final String twineDir = set(Default.TWINE_DIR);
    public final String questsDir = set(Default.QUESTS_DIR);
    public final String jsDir = set(Default.JS_DIR);
    public final String imgDir = set(Default.IMG_DIR);
    public final String usersDir = set(Default.USERS_DIR);
    public final String pagesDir = set(Default.PAGES_DIR);
    public final String attrRoles = set(Default.ATTR_ROLES);
    public final String attrRole = set(Default.ATTR_ROLE);
    public final String attrGame = set(Default.ATTR_GAME);
    public final String attrUser = set(Default.ATTR_USER);
    public final String attrUsers = set(Default.ATTR_USERS);
    public final String attrUserId = set(Default.ATTR_USER_ID);
    public final String attrQuest = set(Default.ATTR_QUEST);
    public final String attrQuestion = set(Default.ATTR_QUESTION);
    public final String attrAnswers = set(Default.ATTR_ANSWERS);
    public final String attrOpenQuests = set(Default.ATTR_OPEN_QUESTS);
    public final String attrAuthors = set(Default.ATTR_AUTHORS);
    public final String attrPageSize = set(Default.ATTR_PAGE_SIZE);
    public final String attrPageNumber = set(Default.ATTR_PAGE_NUMBER);
    public final String attrPageCount = set(Default.ATTR_PAGE_COUNT);
    public final String attrQuests = set(Default.ATTR_QUESTS);
    public final String attrLang = set(Default.ATTR_LANG);
    public final String attrWins = set(Default.ATTR_WINS);
    public final String attrLoses = set(Default.ATTR_LOSES);
    public final String attrUsersRegistered = set(Default.ATTR_USERS_REGISTERED);
    public final String attrGamesPlayed = set(Default.ATTR_GAMES_PLAYED);
    public final String attrQuestCreated = set(Default.ATTR_QUEST_CREATED);
    public final String attrQuestionsCreated = set(Default.ATTR_QUESTIONS_CREATED);
    public final String attrAnswersCreated = set(Default.ATTR_ANSWERS_CREATED);
    public final String attrBestPlayerId = set(Default.ATTR_BEST_PLAYER_ID);
    public final String attrWorstPlayerId = set(Default.ATTR_WORST_PLAYER_ID);
    public final String attrMostPopularQuestId = set(Default.ATTR_MOST_POPULAR_QUEST_ID);
    public final String attrBestPlayerLogin = set(Default.ATTR_BEST_PLAYER_LOGIN);
    public final String attrWorstPlayerLogin = set(Default.ATTR_WORST_PLAYER_LOGIN);
    public final String attrMostPopularQuestName = set(Default.ATTR_MOST_POPULAR_QUEST_NAME);
    public final String attrBestPlayerWins = set(Default.ATTR_BEST_PLAYER_WINS);
    public final String attrWorstPlayerLoses = set(Default.ATTR_WORST_PLAYER_LOSES);
    public final String attrMostPopularQuestLaunches = set(Default.ATTR_MOST_POPULAR_QUEST_LAUNCHES);
    public final String jspEditUser = set(Default.JSP_EDIT_USER);
    public final String jspEditUsers = set(Default.JSP_EDIT_USERS);
    public final String jspEditProfile = set(Default.JSP_EDIT_PROFILE);
    public final String jspEditQuestContent = set(Default.JSP_EDIT_QUEST_CONTENT);
    public final String jspEditQuest = set(Default.JSP_EDIT_QUEST);
    public final String jspQuests = set(Default.JSP_QUESTS);
    public final String jspEditQuests = set(Default.JSP_EDIT_QUESTS);
    public final String jspAbout = set(Default.JSP_ABOUT);
    public final String jspHome = set(Default.JSP_HOME);
    public final String jspStatistics = set(Default.JSP_STATISTICS);
    public final String jspPlay = set(Default.JSP_PLAY);
    public final String jspLogin = set(Default.JSP_LOGIN);
    public final String jspProfile = set(Default.JSP_PROFILE);
    public final String zero = set(Default.ZERO);
    public final String[] emptyStringArray = set(Default.EMPTY_STRING_ARRAY);
    public final String deleted = set(Default.DELETED);
    public final String notExist = set(Default.NOT_EXIST);
    public final String notFoundCmd = set(Default.NOT_FOUND_CMD);
    public final String incorrectUri = set(Default.INCORRECT_URI);
    public final String emptyPassword = set(Default.EMPTY_PASSWORD);
    public final int playStartAgainValue = set(Default.PLAY_START_AGAIN_VALUE);

    //==================================== /DATA ==============================================
    public final int playCompleteValue = set(Default.PLAY_COMPLETE_VALUE);

    //================================ INIT ========================================
    private Setting() {
        updateFromYAML();
    }
    //============================ /SAFE_THREAD_SINGLETON ====================================

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

    private static <T> T set(T value) {
        return value;
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
}
//=============================== /INIT ========================================
