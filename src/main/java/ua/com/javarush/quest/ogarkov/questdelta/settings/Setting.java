package ua.com.javarush.quest.ogarkov.questdelta.settings;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

//    private static final String SETTING_YAML = "ogarkov/setting.yaml";

    //==================================== DATA ===============================================
    private int defaultPageSize;
    private String root;
    private String users;
    private String login;
    private String signup;
    private String profile;
    private String logout;
    private String user;
    private String editQuests;
    private String editQuestContent;
    private String editQuest;
    private String questionCreate;
    private String about;
    private String paramId;
    private String paramPageNumber;
    private String paramPageSize;
    private String paramName;
    private String paramLang;
    private String paramQuestionIndex;
    private String paramQuestionDelete;
    private String paramQuestionCreate;
    private String paramQuestionUpdate;
    private String paramQuestDelete;
    private String paramAnswerDelete;
    private String inputImage;
    private String inputLogin;
    private String inputKeyword;
    private String inputPassword;
    private String inputRole;
    private String inputText;
    private String inputAnswer;
    private String inputNextQuestionId;
    private String inputGameState;
    private String imgDir;
    private String profileEdit;
    private String userEdit;
    private String signInBtn;
    private String updateBtn;
    private String createBtn;
    private String deleteBtn;
    private String answerCreateBtn;
    private String zero;
    private String quests;
    private String play;
    private String statistics;
    private String jsDir;
    private String imageAbout;
    private String bootstrap;
    private String insertParam;
    private String postToUrl;
    private String formValidation;
    private String defaultAvatar;
    private String defaultImage;
    private String helpDir;
    private String twineDir;
    private Language defaultLanguage;
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
        loadFromDefault();
//        updateFromYAML();
    }

    private void loadFromDefault() {
        Field[] settingFields = Setting.class.getDeclaredFields();
        Field[] defaultFields = Default.class.getDeclaredFields();
        for (Field settingField : settingFields) {
//            settingField.setAccessible(true);
            String settingFieldName = settingField.getName().toLowerCase();
            for (Field defaultField : defaultFields) {
                String defaultFieldName = defaultField.getName().replaceAll("_", "").toLowerCase();
                if (settingFieldName.equals(defaultFieldName)) {
                    try {
                        defaultField.setAccessible(true);
                        settingField.set(this, defaultField.get(null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } finally {
                        defaultField.setAccessible(false);
                    }
                }
            }
//            settingField.setAccessible(false);
        }
    }

    private void updateFromYAML() {
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//        ObjectReader readerForSettingUpdating = mapper.readerForUpdating(this);
//        URL settingResource = Setting.class.getClassLoader().getResource(SETTING_YAML);
//        URL foodRationResource = Setting.class.getClassLoader().getResource(FOOD_RATION_YAML);
//        if (Objects.nonNull(settingResource) && Objects.nonNull(foodRationResource)) {
//            try {
//                readerForSettingUpdating.readValue(settingResource.openStream());
//                readerForSettingUpdating.readValue(foodRationResource.openStream());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
    //=============================== /INIT ========================================
}
