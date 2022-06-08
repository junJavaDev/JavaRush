package ru.javarush.ogarkov.islandsimulation.item;

import javafx.scene.image.Image;

public enum Items {
    BEAR(getImage("/flora/carnivore/bear.png")),
    BOA(getImage("/flora/carnivore/boa.png")),
    EAGLE(getImage("/flora/carnivore/eagle.png")),
    FOX(getImage("/flora/carnivore/fox.png")),
    WOLF(getImage("/flora/carnivore/wolf.png")),
    BOAR(getImage("/flora/herbivore/boar.png")),
    BUFFALO(getImage("/flora/herbivore/buffalo.png")),
    CATERPILLAR(getImage("/flora/herbivore/caterpillar.png")),
    DEER(getImage("/flora/herbivore/deer.png")),
    DUCK(getImage("/flora/herbivore/duck.png")),
    GOAT(getImage("/flora/herbivore/goat.png")),
    HORSE(getImage("/flora/herbivore/horse.png")),
    MOUSE(getImage("/flora/herbivore/mouse.png")),
    RABBIT(getImage("/flora/herbivore/rabbit.png")),
    SHEEP(getImage("/flora/herbivore/sheep.png")),
    BUSH(getImage("/fauna/bush.png")),
    DANDELION(getImage("/fauna/dandelion.png")),
    FLOWER(getImage("/fauna/flower.png")),
    GRASS(getImage("/fauna/grass.png")),
    SOIL(getImage("/fauna/soil.png")),
    SPROUT(getImage("/fauna/sprout.png")),
    SUNFLOWER(getImage("/fauna/sunflower.png")),
    TREE(getImage("/fauna/tree.png"));

    private final Image icon;

    Items(Image icon) {
        this.icon = icon;
    }

    private static Image getImage(String path) {
        return new Image(String.valueOf(Items.class.getResource(path)));
    }

    public Image getIcon() {
        return icon;
    }
}
