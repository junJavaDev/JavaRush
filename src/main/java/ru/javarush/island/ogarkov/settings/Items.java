package ru.javarush.island.ogarkov.settings;

import javafx.scene.image.Image;
import ru.javarush.island.ogarkov.repository.itemfactory.Factory;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore.*;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore.*;
import ru.javarush.island.ogarkov.repository.itemfactory.landform.LandformFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.landform.PlainFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.plant.*;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum Items {
    ANIMAL(null, new AnimalFactory()),
        CARNIVORE(ANIMAL, new CarnivoreFactory()),
            BEAR(CARNIVORE, new BearFactory()),
            BOA(CARNIVORE, new BoaFactory()),
            EAGLE(CARNIVORE, new EagleFactory()),
            FOX(CARNIVORE, new FoxFactory()),
            WOLF(CARNIVORE, new WolfFactory()),
        HERBIVORE(ANIMAL, new HerbivoreFactory()),
            BOAR(HERBIVORE, new BoarFactory()),
            BUFFALO(HERBIVORE, new BuffaloFactory()),
            CATERPILLAR(HERBIVORE, new CaterpillarFactory()),
            DEER(HERBIVORE, new DeerFactory()),
            DUCK(HERBIVORE, new DuckFactory()),
            GOAT(HERBIVORE, new GoatFactory()),
            HORSE(HERBIVORE, new HorseFactory()),
            MOUSE(HERBIVORE, new MouseFactory()),
            RABBIT(HERBIVORE, new RabbitFactory()),
            SHEEP(HERBIVORE, new SheepFactory()),
    PLANT(null, new PlantFactory()),
            BUSH(PLANT, new BushFactory()),
            DANDELION(PLANT, new DandelionFactory()),
            FLOWER(PLANT, new FlowerFactory()),
            GRASS(PLANT, new GrassFactory()),
            SPROUT(PLANT, new SproutFactory()),
            TREE(PLANT, new TreeFactory()),
    LANDFORM(null, new LandformFactory()),
            PLAIN(LANDFORM, new PlainFactory());

    private final Factory factory;
    private final Items parent;
    private String name;
    private double maxWeight;
    private int maxCount;
    private int maxSpeed;
    private double maxFood;
    private Image icon;
    private Map<Items, Integer> foodRation;
    private final List<Items> children = new ArrayList<>();

    Items(Items parent, Factory factory) {
        this.parent = parent;
        this.factory = factory;
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

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void setFoodRation(Map<Items, Integer> foodRation) {
        this.foodRation = foodRation;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxFood(double maxFood) {
        this.maxFood = maxFood;
    }

    public void setName(String name) {
        this.name = name;
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
