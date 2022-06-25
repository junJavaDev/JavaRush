package com.javarush.island.ogarkov.settings;

import com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore.*;
import com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore.*;
import com.javarush.island.ogarkov.repository.itemfactory.plant.*;
import com.javarush.island.ogarkov.util.Randomizer;
import javafx.scene.image.Image;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import com.javarush.island.ogarkov.repository.itemfactory.landform.LandformFactory;
import com.javarush.island.ogarkov.repository.itemfactory.landform.PlainFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.javarush.island.ogarkov.settings.FoodRation.*;

public enum Items {
    ANIMAL(null, new AnimalFactory()),
        CARNIVORE(ANIMAL, new CarnivoreFactory()),
            BEAR("Медведь", assignIcon("/carnivore/bear.png"), 500, 5, 2, 80, CARNIVORE, BEAR_FOOD, new BearFactory()),
            BOA("Удав", assignIcon("/carnivore/boa.png"), 15, 30, 1, 3, CARNIVORE, BOA_FOOD, new BoaFactory()),
            EAGLE("Орёл", assignIcon("/carnivore/eagle.png"), 6, 20, 3, 1, CARNIVORE, EAGLE_FOOD, new EagleFactory()),
            FOX("Лиса", assignIcon("/carnivore/fox.png"), 8, 30, 2, 2, CARNIVORE, FOX_FOOD, new FoxFactory()),
            WOLF("Волк", assignIcon("/carnivore/wolf.png"), 50, 30, 3, 8, CARNIVORE, WOLF_FOOD, new WolfFactory()),
        HERBIVORE(ANIMAL, new HerbivoreFactory()),
            BOAR("Кабан", assignIcon("/herbivore/boar.png"), 400, 50, 2, 50, HERBIVORE, BOAR_FOOD, new BoarFactory()),
            BUFFALO("Буйвол", assignIcon("/herbivore/buffalo.png"), 700, 10, 3, 100, HERBIVORE, HERBIVORE_FOOD, new BuffaloFactory()),
            CATERPILLAR("Гусеница", assignIcon("/herbivore/caterpillar.png"), 0.01, 1000, 0, 0, HERBIVORE, HERBIVORE_FOOD, new CaterpillarFactory()),
            DEER("Олень", assignIcon("/herbivore/deer.png"), 300, 20, 4, 50, HERBIVORE, HERBIVORE_FOOD, new DeerFactory()),
            DUCK("Утка", assignIcon("/herbivore/duck.png"), 1, 200, 4, 0.15, HERBIVORE, HERBIVORE_FOOD, new DuckFactory()),
            GOAT("Коза", assignIcon("/herbivore/goat.png"), 60, 140, 3, 10, HERBIVORE, HERBIVORE_FOOD, new GoatFactory()),
            HORSE("Лошадь", assignIcon("/herbivore/horse.png"), 400, 20, 4, 60, HERBIVORE, HERBIVORE_FOOD, new HorseFactory()),
            MOUSE("Мышь", assignIcon("/herbivore/mouse.png"), 0.05, 500, 1, 0.01, HERBIVORE, HERBIVORE_FOOD, new MouseFactory()),
            RABBIT("Кролик", assignIcon("/herbivore/rabbit.png"), 2, 150, 2, 0.45, HERBIVORE, HERBIVORE_FOOD, new RabbitFactory()),
            SHEEP("Овца", assignIcon("/herbivore/sheep.png"), 70, 140, 3, 15, HERBIVORE, HERBIVORE_FOOD, new SheepFactory()),
    PLANT(null, new PlantFactory()),
            BUSH("Куст", assignIcon("/plant/bush.png"), 1, 200, PLANT, new BushFactory()),
            DANDELION("Одуванчик", assignIcon("/plant/dandelion.png"), 1, 200, PLANT, new DandelionFactory()),
            FLOWER("Цветок", assignIcon("/plant/flower.png"), 1, 200, PLANT, new FlowerFactory()),
            GRASS("Трава", assignIcon("/plant/grass.png"), 1, 200, PLANT, new GrassFactory()),
            SPROUT("Росток", assignIcon("/plant/sprout.png"), 1, 200, PLANT, new SproutFactory()),
            TREE("Дерево", assignIcon("/plant/tree.png"), 1, 200, PLANT, new TreeFactory()),
    LANDFORM(null, new LandformFactory()),
            PLAIN("Равнина", assignIcon("/landform/plain.png"), 1, 1, LANDFORM, new PlainFactory());


    static {
        FoodRation.init();
    }

    private final Factory factory;
    private final Items parent;
    private Image icon;
    private double maxWeight;
    private int maxCount;
    private int maxSpeed;
    private double maxFood;
    private String name;
    private Map<Items, Integer> foodRation;
    private final List<Items> children = new ArrayList<>();

    Items(Items parent, Factory factory) {
        this.parent = parent;
        this.factory = factory;
        addToParentChildren();
    }

    Items(String name, Image icon, double maxWeight, int maxCount, Items parent, Factory factory) {
        this(parent, factory);
        this.name = name;
        this.maxWeight = maxWeight;
        this.maxCount = maxCount;
        this.icon = icon;
        addToParentChildren();
    }

    Items(String name, Image icon, double maxWeight, int maxCount, int maxSpeed, double maxFood, Items parent, Map<Items, Integer> foodRation, Factory factory) {
        this(name, icon, maxWeight, maxCount, parent, factory);
        this.maxSpeed = maxSpeed;
        this.maxFood = maxFood;
        this.foodRation = foodRation;
        addToParentChildren();
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxFood() {
        return maxFood;
    }

    public Map<Items, Integer> getFoodRation() {
        return foodRation;
    }

    private static Image assignIcon(String path) {
        return new Image(String.valueOf(Items.class.getResource(path)));
    }

    public Image getIcon() {
        return icon;
    }

    public List<Items> getChildren() {
        return children;
    }

    public boolean is(Items other) {
        boolean result = (this == other);
        if (this.parent != null) {
            result = (result || this.parent.is(other));
        }
        return result;
    }

    public boolean isNot(Items other) {
        return !this.is(other);
    }

    public Factory getFactory() {
        return factory;
    }

    public Items getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    private void addToParentChildren() {
        if (this.parent != null) {
            this.parent.children.add(this);
        }
    }

    public Items getRandom() {
        Items randomItem = this;
        if (!randomItem.getChildren().isEmpty()) {
            int randomItemIndex = Randomizer.getInt(getChildren().size());
            randomItem = children.get(randomItemIndex).getRandom();
        }
        return randomItem;
    }

    public static List<Items> getOrganismItems() {
        List<Items> organismItems = new ArrayList<>();
        organismItems.addAll(CARNIVORE.getChildren());
        organismItems.addAll(HERBIVORE.getChildren());
        organismItems.addAll(PLANT.getChildren());
        organismItems.addAll(LANDFORM.getChildren());
        return organismItems;
    }
}
