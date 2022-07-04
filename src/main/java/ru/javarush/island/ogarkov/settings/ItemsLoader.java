package ru.javarush.island.ogarkov.settings;

import javafx.scene.image.Image;
import org.reflections.Reflections;
import ru.javarush.island.ogarkov.annotations.ItemData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemsLoader {

    private ItemsLoader() {
    }

    public static void loadItems() {
        Reflections reflections = new Reflections(Setting.get().getEntityPath());
        Set<Class<?>> itemsClasses = reflections.getTypesAnnotatedWith(ItemData.class);
        itemsClasses.forEach(ItemsLoader::loadItemData);
        loadFoodRations();
    }

    private static void loadItemData(Class<?> clazz) {
        ItemData itemData = clazz.getAnnotation(ItemData.class);
        Items item = Items.valueOf(clazz.getSimpleName().toUpperCase());
        item.setName(itemData.name());
        item.setMaxWeight(itemData.maxWeight());
        item.setMaxCount(itemData.maxCount());
        item.setMaxSpeed(itemData.maxSpeed());
        item.setMaxFood(itemData.maxFood());
        item.setIcon(new Image(String.valueOf(ItemsLoader.class.getResource(itemData.icon()))));
    }

    private static void loadFoodRations() {
        for (String[][] strings : Setting.get().getFoodRation()) {
            Items item = Items.valueOf(strings[0][0].toUpperCase());
            Map<Items, Integer> foodRation = new HashMap<>();
            for (int probabilityIndex = 0; probabilityIndex < strings[1].length; probabilityIndex++) {
                String[] entryProbability = strings[1][probabilityIndex].split(":");
                Items foodItem = Items.valueOf(entryProbability[0].toUpperCase());
                int foodItemProbability = Integer.parseInt(entryProbability[1]);
                foodRation.put(foodItem, foodItemProbability);
            }
            item.setFoodRation(foodRation);
        }
    }
}
