package ru.javarush.ogarkov.islandsimulation.settings;

import javafx.scene.image.Image;
import ru.javarush.ogarkov.islandsimulation.factory.*;
import ru.javarush.ogarkov.islandsimulation.factory.carnivore.*;
import ru.javarush.ogarkov.islandsimulation.factory.herbivore.*;
import ru.javarush.ogarkov.islandsimulation.factory.landform.PlainFactory;
import ru.javarush.ogarkov.islandsimulation.factory.plant.*;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.javarush.ogarkov.islandsimulation.settings.EatingProbability.*;

public enum Items {
    CARNIVORE(new CarnivoreFactory()),
        BEAR(CARNIVORE, 500, 5, 2, 80, BEAR_FOOD, new BearFactory(), getImage("/carnivore/bear.png")),
        BOA(CARNIVORE, 15, 30, 1, 3, BOA_FOOD, new BoaFactory(), getImage("/carnivore/boa.png")),
        EAGLE(CARNIVORE, 6, 20, 3, 1, EAGLE_FOOD, new EagleFactory(), getImage("/carnivore/eagle.png")),
        FOX(CARNIVORE,8, 30, 2, 2, FOX_FOOD, new FoxFactory(), getImage("/carnivore/fox.png")),
        WOLF(CARNIVORE,50, 30, 3, 8, WOLF_FOOD, new WolfFactory(), getImage("/carnivore/wolf.png")),
    HERBIVORE(new HerbivoreFactory()),
        BOAR(HERBIVORE,400, 50, 2, 50, BOAR_FOOD, new BoarFactory(), getImage("/herbivore/boar.png")),
        BUFFALO(HERBIVORE,700, 10, 3, 100, HERBIVORE_FOOD, new BuffaloFactory(), getImage("/herbivore/buffalo.png")),
        CATERPILLAR(HERBIVORE,0.01, 1000, 0, 0, HERBIVORE_FOOD, new CaterpillarFactory(), getImage("/herbivore/caterpillar.png")),
        DEER(HERBIVORE,300, 20, 4, 50, HERBIVORE_FOOD, new DeerFactory(), getImage("/herbivore/deer.png")),
        DUCK(HERBIVORE,1, 200, 4, 0.15, HERBIVORE_FOOD, new DuckFactory(), getImage("/herbivore/duck.png")),
        GOAT(HERBIVORE,60, 140, 3, 10, HERBIVORE_FOOD, new GoatFactory(), getImage("/herbivore/goat.png")),
        HORSE(HERBIVORE,400, 20, 4, 60, HERBIVORE_FOOD, new HorseFactory(), getImage("/herbivore/horse.png")),
        MOUSE(HERBIVORE,0.05, 500, 1, 0.01, HERBIVORE_FOOD, new MouseFactory(), getImage("/herbivore/mouse.png")),
        RABBIT(HERBIVORE,2, 150, 2, 0.45, HERBIVORE_FOOD, new RabbitFactory(), getImage("/herbivore/rabbit.png")),
        SHEEP(HERBIVORE,70, 140, 3, 15, HERBIVORE_FOOD, new SheepFactory(), getImage("/herbivore/sheep.png")),
    PLANT(new PlantFactory()),
        BUSH(PLANT,1, 200, new BushFactory(), getImage("/plant/bush.png")),
        DANDELION(PLANT,1, 200, new DandelionFactory(), getImage("/plant/dandelion.png")),
        FLOWER(PLANT,1, 200, new FlowerFactory(), getImage("/plant/flower.png")),
        GRASS(PLANT,1, 200, new GrassFactory(), getImage("/plant/grass.png")),
        SPROUT(PLANT,1, 200, new SproutFactory(), getImage("/plant/sprout.png")),
        TREE(PLANT,1, 200, new TreeFactory(), getImage("/plant/tree.png")),
    LANDFORM(new LandformFactory()),
        PLAIN(LANDFORM,1, 200, new PlainFactory(), getImage("/landform/plain.png"));


    static {
        EatingProbability.init();
    }

    private Factory factory;
    private Items parent;
    private Image icon;
    private double weight;
    private int maxPerLocation;
    private int maxSpeed;
    private double foodPerSatiation;
    private Map<Items, Integer> eatingProbability;
    private List<Items> children = new ArrayList<>();

    Items(Items parent, double weight, int maxPerLocation, int maxSpeed, double foodPerSatiation, Map<Items, Integer> eatingProbability, Factory factory, Image icon) {
        this.parent = parent;
        if (this.parent != null) {
            this.parent.addChild(this);
        }
        this.icon = icon;
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.maxSpeed = maxSpeed;
        this.foodPerSatiation = foodPerSatiation;
        this.eatingProbability = eatingProbability;
        this.factory = factory;
    }


    Items(Items parent, double weight, int maxPerLocation, Factory factory, Image icon) {
        this.parent = parent;
        if (this.parent != null) {
            this.parent.addChild(this);
        }
        this.icon = icon;
        this.weight = weight;
        this.factory = factory;
    }

    Items(Factory factory) {
        this.factory = factory;
    }

    private void addChild(Items child) {
        children.add(child);
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

    public Map<Items, Integer> getEatingProbability() {
        return eatingProbability;
    }

    private static Image getImage(String path) {
        return new Image(String.valueOf(Items.class.getResource(path)));
    }

    public Image getIcon() {
        return icon;
    }

    public List<Items> getChildren() {
        return children;
    }
    public BasicItem createItem() {
        return factory.createItem(this);
    }
}
