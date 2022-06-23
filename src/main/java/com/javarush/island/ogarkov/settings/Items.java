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
            BEAR("Медведь",CARNIVORE, 500, 5, 2, 80, BEAR_FOOD, new BearFactory(), assignIcon("/carnivore/bear.png")),
            BOA("Удав",CARNIVORE, 15, 30, 1, 3, BOA_FOOD, new BoaFactory(), assignIcon("/carnivore/boa.png")),
            EAGLE("Орёл",CARNIVORE, 6, 20, 3, 1, EAGLE_FOOD, new EagleFactory(), assignIcon("/carnivore/eagle.png")),
            FOX("Лиса",CARNIVORE,8, 30, 2, 2, FOX_FOOD, new FoxFactory(), assignIcon("/carnivore/fox.png")),
            WOLF("Волк",CARNIVORE,50, 30, 3, 8, WOLF_FOOD, new WolfFactory(), assignIcon("/carnivore/wolf.png")),
        HERBIVORE(ANIMAL, new HerbivoreFactory()),
            BOAR("Кабан",HERBIVORE,400, 50, 2, 50, BOAR_FOOD, new BoarFactory(), assignIcon("/herbivore/boar.png")),
            BUFFALO("Буйвол",HERBIVORE,700, 10, 3, 100, HERBIVORE_FOOD, new BuffaloFactory(), assignIcon("/herbivore/buffalo.png")),
            CATERPILLAR("Гусеница",HERBIVORE,0.01, 1000, 0, 0, HERBIVORE_FOOD, new CaterpillarFactory(), assignIcon("/herbivore/caterpillar.png")),
            DEER("Олень",HERBIVORE,300, 20, 4, 50, HERBIVORE_FOOD, new DeerFactory(), assignIcon("/herbivore/deer.png")),
            DUCK("Утка",HERBIVORE,1, 200, 4, 0.15, HERBIVORE_FOOD, new DuckFactory(), assignIcon("/herbivore/duck.png")),
            GOAT("Коза",HERBIVORE,60, 140, 3, 10, HERBIVORE_FOOD, new GoatFactory(), assignIcon("/herbivore/goat.png")),
            HORSE("Лошадь",HERBIVORE,400, 20, 4, 60, HERBIVORE_FOOD, new HorseFactory(), assignIcon("/herbivore/horse.png")),
            MOUSE("Мышь",HERBIVORE,0.05, 500, 1, 0.01, HERBIVORE_FOOD, new MouseFactory(), assignIcon("/herbivore/mouse.png")),
            RABBIT("Кролик",HERBIVORE,2, 150, 2, 0.45, HERBIVORE_FOOD, new RabbitFactory(), assignIcon("/herbivore/rabbit.png")),
            SHEEP("Овца",HERBIVORE,70, 140, 3, 15, HERBIVORE_FOOD, new SheepFactory(), assignIcon("/herbivore/sheep.png")),
    PLANT(null, new PlantFactory()),
        BUSH("Куст", PLANT, 1, 200, new BushFactory(), assignIcon("/plant/bush.png")),
        DANDELION("Одуванчик", PLANT, 1, 200, new DandelionFactory(), assignIcon("/plant/dandelion.png")),
        FLOWER("Цветок", PLANT, 1, 200, new FlowerFactory(), assignIcon("/plant/flower.png")),
        GRASS("Трава", PLANT, 1, 200, new GrassFactory(), assignIcon("/plant/grass.png")),
        SPROUT("Росток", PLANT, 1, 200, new SproutFactory(), assignIcon("/plant/sprout.png")),
        TREE("Дерево", PLANT, 1, 200, new TreeFactory(), assignIcon("/plant/tree.png")),
    LANDFORM(null, new LandformFactory()),
        PLAIN("Равнина", LANDFORM, 1, 200, new PlainFactory(), assignIcon("/landform/plain.png"));


    static {
        FoodRation.init();
    }

    private final Factory factory;
    private final Items parent;
    private Image icon;
    private double weight;
    private int maxPerLocation;
    private int maxSpeed;
    private double foodPerSatiation;
    private String name;
    private Map<Items, Integer> foodRation;
    private final List<Items> children = new ArrayList<>();

    Items(Items parent, Factory factory) {
        this.parent = parent;
        this.factory = factory;
        addToParentChildren();
    }

    Items(String name, Items parent, double weight, int maxPerLocation, Factory factory, Image icon) {
        this(parent, factory);
        this.name = name;
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.icon = icon;
        addToParentChildren();
    }

    Items(String name, Items parent, double weight, int maxPerLocation, int maxSpeed, double foodPerSatiation, Map<Items, Integer> foodRation, Factory factory, Image icon) {
        this(name, parent, weight, maxPerLocation, factory, icon);
        this.maxSpeed = maxSpeed;
        this.foodPerSatiation = foodPerSatiation;
        this.foodRation = foodRation;
        addToParentChildren();
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPerLocation() {
        return maxPerLocation;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getFoodPerSatiation() {
        return foodPerSatiation;
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
            int randomItemIndex = Randomizer.getIntOriginOne(getChildren().size());
            randomItem = children.get(randomItemIndex).getRandom();
        }
        return randomItem;
    }



}
