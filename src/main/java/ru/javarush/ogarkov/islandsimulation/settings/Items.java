package ru.javarush.ogarkov.islandsimulation.settings;

import javafx.scene.image.Image;

import java.util.Map;

import static ru.javarush.ogarkov.islandsimulation.settings.EatingProbability.*;

public enum Items {
    CARNIVORE,
        BEAR(CARNIVORE, 500, 5, 2, 80, BEAR_FOOD, getImage("/flora/carnivore/bear.png")),
        BOA(CARNIVORE, 15, 30, 1, 3, BOA_FOOD, getImage("/flora/carnivore/boa.png")),
        EAGLE(CARNIVORE, 6, 20, 3, 1, EAGLE_FOOD, getImage("/flora/carnivore/eagle.png")),
        FOX(CARNIVORE,8, 30, 2, 2, FOX_FOOD, getImage("/flora/carnivore/fox.png")),
        WOLF(CARNIVORE,50, 30, 3, 8, WOLF_FOOD, getImage("/flora/carnivore/wolf.png")),
        BOAR(CARNIVORE,400, 50, 2, 50, BOAR_FOOD, getImage("/flora/herbivore/boar.png")),
    HERBIVORE,
        BUFFALO(HERBIVORE,700, 10, 3, 100, HERBIVORE_FOOD, getImage("/flora/herbivore/buffalo.png")),
        CATERPILLAR(HERBIVORE,0.01, 1000, 0, 0, HERBIVORE_FOOD, getImage("/flora/herbivore/caterpillar.png")),
        DEER(HERBIVORE,300, 20, 4, 50, HERBIVORE_FOOD, getImage("/flora/herbivore/deer.png")),
        DUCK(HERBIVORE,1, 200, 4, 0.15, HERBIVORE_FOOD, getImage("/flora/herbivore/duck.png")),
        GOAT(HERBIVORE,60, 140, 3, 10, HERBIVORE_FOOD, getImage("/flora/herbivore/goat.png")),
        HORSE(HERBIVORE,400, 20, 4, 60, HERBIVORE_FOOD, getImage("/flora/herbivore/horse.png")),
        MOUSE(HERBIVORE,0.05, 500, 1, 0.01, HERBIVORE_FOOD, getImage("/flora/herbivore/mouse.png")),
        RABBIT(HERBIVORE,2, 150, 2, 0.45, HERBIVORE_FOOD, getImage("/flora/herbivore/rabbit.png")),
        SHEEP(HERBIVORE,70, 140, 3, 15, HERBIVORE_FOOD, getImage("/flora/herbivore/sheep.png")),
    PLANT,
        BUSH(PLANT,1, 200, getImage("/fauna/bush.png")),
        DANDELION(PLANT,1, 200, getImage("/fauna/dandelion.png")),
        FLOWER(PLANT,1, 200, getImage("/fauna/flower.png")),
        GRASS(PLANT,1, 200, getImage("/fauna/grass.png")),
        SPROUT(PLANT,1, 200, getImage("/fauna/sprout.png")),
        TREE(PLANT,1, 200, getImage("/fauna/tree.png")),
        SOIL(PLANT,1, 200, getImage("/fauna/soil.png"));

    static {
        EatingProbability.init();
    }

    private Items parent;
    private Image icon;
    private double weight;
    private int maxPerLocation;
    private int maxSpeed;
    private double foodPerSatiation;
    private Map<Items, Integer> eatingProbability;

    Items(Items parent, double weight, int maxPerLocation, int maxSpeed, double foodPerSatiation, Map<Items, Integer> eatingProbability, Image icon) {
        this.parent = parent;
        this.icon = icon;
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.maxSpeed = maxSpeed;
        this.foodPerSatiation = foodPerSatiation;
        this.eatingProbability = eatingProbability;
    }

    Items(Items parent, double weight, int maxPerLocation, Image icon) {
        this.parent = parent;
        this.icon = icon;
        this.weight = weight;
    }

    Items() {
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
}
