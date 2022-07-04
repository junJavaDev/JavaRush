package ru.javarush.island.ogarkov.settings;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Objects;

@Getter
@Setter(AccessLevel.PROTECTED)
public class Setting {

    private static final String SETTING_YAML = "ogarkov/setting.yaml";
    private static final String FOOD_RATION_YAML = "ogarkov/food_ration.yaml";

    //TODO ---  JSON???? Just save to cfg file
    // OK, added YAML config files

    //==================================== DATA ===============================================
    private String simulationName;
    private String entityPath;
    private int islandRows;
    private int islandCols;
    private int territoryRows;
    private int territoryCols;
    private int initialDelay;
    private int mainDelay;
    private int tryingLockMillis;
    private int carnivoreInitPerCell;
    private int herbivoreInitPerCell;
    private int plantInitPerCell;
    private int plantSpawnedPerEmptyCell;
    private int cellCarnivoreProbability;
    private int cellHerbivoreProbability;
    private int cellPlantProbability;
    private double initSatiety;
    private double herbivoreHunger;
    private double carnivoreHunger;
    private double initWeight;
    private double losingWeightPercent;
    private double carnivoreWeightToReproduce;
    private double herbivoreWeightToReproduce;
    private int lowerChanceToReproduce;
    private int higherChanceToReproduce;
    private int carnivoreChanceToReproduce;
    private int herbivoreChanceToReproduce;
    private int plantChanceToReproduce;
    private int carnivoreLifeLength;
    private int herbivoreLifeLength;
    private int plantLifeLength;
    private int landformLifeLength;
    private int statisticsLineSpacing;
    private int islandCellWidth;
    private int islandCellHeight;
    private int territoryCellWidth;
    private int territoryCellHeight;
    private int statisticsLineHeight;
    private int statisticsFieldSpacing;
    private int statisticsFieldWidth;
    private int statisticsIconSize;
    private int islandGridSize;
    private int territoryGridSize;
    private int diagramMaxPlants;
    private int diagramMaxAnimals;
    private String[][][] foodRation;
    @JsonIgnore
    private Color defaultIslandColor;
    @JsonIgnore
    private Color defaultTerritoryColor;
    @JsonIgnore
    private Color selectedColor;
    @JsonIgnore
    private Color diagramMaxColor;
    @JsonIgnore
    private Color diagramMiddleColor;
    @JsonIgnore
    private Color diagramMinColor;
    @JsonIgnore
    private Color statisticsColor;
    @JsonIgnore
    private Image aliveIcon;
    @JsonIgnore
    private Image deadIcon;
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
        updateFromYAML();
    }

    private void loadFromDefault() {
        Field[] settingFields = Setting.class.getDeclaredFields();
        Field[] defaultFields = Default.class.getDeclaredFields();
        for (Field settingField : settingFields) {
            String settingFieldName = settingField.getName().toLowerCase();
            for (Field defaultField : defaultFields) {
                String defaultFieldName = defaultField.getName().replaceAll("_", "").toLowerCase();
                if (settingFieldName.equals(defaultFieldName)) {
                    try {
                        settingField.set(this, defaultField.get(null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void updateFromYAML() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        ObjectReader readerForSettingUpdating = mapper.readerForUpdating(this);
        URL settingResource = Setting.class.getClassLoader().getResource(SETTING_YAML);
        URL foodRationResource = Setting.class.getClassLoader().getResource(FOOD_RATION_YAML);
        if (Objects.nonNull(settingResource) && Objects.nonNull(foodRationResource)) {
            try {
                readerForSettingUpdating.readValue(settingResource.openStream());
                readerForSettingUpdating.readValue(foodRationResource.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //=============================== /INIT ========================================
}
