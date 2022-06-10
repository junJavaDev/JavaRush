package ru.javarush.ogarkov.islandsimulation.settings;

import javafx.scene.image.Image;

import java.util.Map;

// TODO: 11.06.2022 Разобраться что создавать сначала, что потом
public enum Items {
    BEAR(500, 5, 2, 80, EatingProbability.BEAR_FOOD, getImage("/flora/carnivore/bear.png")),
    BOA(15, 30, 1, 3, EatingProbability.BOA_FOOD, getImage("/flora/carnivore/boa.png")),
    EAGLE(6, 20, 3, 1, EatingProbability.EAGLE_FOOD, getImage("/flora/carnivore/eagle.png")),
    FOX(8, 30, 2, 2, EatingProbability.FOX_FOOD, getImage("/flora/carnivore/fox.png")),
    WOLF(50, 30, 3, 8, EatingProbability.WOLF_FOOD, getImage("/flora/carnivore/wolf.png")),
    BOAR(400, 50, 2, 50, EatingProbability.BOAR_FOOD, getImage("/flora/herbivore/boar.png")),
    BUFFALO(700, 10, 3, 100, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/buffalo.png")),
    CATERPILLAR(0.01, 1000, 0, 0, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/caterpillar.png")),
    DEER(300, 20, 4, 50, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/deer.png")),
    DUCK(1, 200, 4, 0.15, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/duck.png")),
    GOAT(60, 140, 3, 10, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/goat.png")),
    HORSE(400, 20, 4, 60, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/horse.png")),
    MOUSE(0.05, 500, 1, 0.01, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/mouse.png")),
    RABBIT(2, 150, 2, 0.45, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/rabbit.png")),
    SHEEP(70, 140, 3, 15, EatingProbability.HERBIVORE_FOOD, getImage("/flora/herbivore/sheep.png")),
    BUSH(1, 200, getImage("/fauna/bush.png")),
    DANDELION(1, 200, getImage("/fauna/dandelion.png")),
    FLOWER(1, 200, getImage("/fauna/flower.png")),
    GRASS(1, 200, getImage("/fauna/grass.png")),
    SPROUT(1, 200, getImage("/fauna/sprout.png")),
    TREE(1, 200, getImage("/fauna/tree.png")),
    SOIL(1, 200, getImage("/fauna/soil.png"));

    static {
        EatingProbability.initialize();
    }

    private final Image icon;
    private final double weight;
    private final int maxPerLocation;
    private final int maxSpeed;
    private final double foodPerSatiation;
    private final Map<Items, Integer> eatingProbability;

    Items(double weight, int maxPerLocation, int maxSpeed, double foodPerSatiation, Map<Items, Integer> eatingProbability, Image icon) {
        System.out.println("is 1st constructor Items");
        this.icon = icon;
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.maxSpeed = maxSpeed;
        this.foodPerSatiation = foodPerSatiation;
        this.eatingProbability = eatingProbability;
    }

    Items(double weight, int maxPerLocation, Image icon) {
        System.out.println("is 2nd constructor Items");

        this.icon = icon;
        this.weight = weight;
        this.maxPerLocation = maxPerLocation;
        this.maxSpeed = 0;
        this.foodPerSatiation = 0;
        this.eatingProbability = null;
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
