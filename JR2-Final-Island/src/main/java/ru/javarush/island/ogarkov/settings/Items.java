package ru.javarush.island.ogarkov.settings;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.ogarkov.repository.itemfactory.Factory;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore.*;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore.*;
import ru.javarush.island.ogarkov.repository.itemfactory.landform.LandformFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.landform.PlainFactory;
import ru.javarush.island.ogarkov.repository.itemfactory.plant.*;
import ru.javarush.island.ogarkov.util.Randomizer;

import java.util.*;

@Getter
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
    private final Items higherItem;
    private final List<Items> lowerItems = new ArrayList<>();
    @Setter
    private String name;
    @Setter
    private double maxWeight;
    @Setter
    private int maxCount;
    @Setter
    private int maxSpeed;
    @Setter
    private double maxFood;
    @Setter
    private Image icon;
    @Setter
    private Map<Items, Integer> foodRation;

    Items(Items higherItem, Factory factory) {
        this.higherItem = higherItem;
        this.factory = factory;
        addToHigherItem();
    }

    public static Set<Items> getAllLowerItems() {
        Set<Items> organismItems = new HashSet<>();
        organismItems.addAll(CARNIVORE.getLowerItems());
        organismItems.addAll(HERBIVORE.getLowerItems());
        organismItems.addAll(PLANT.getLowerItems());
        organismItems.addAll(LANDFORM.getLowerItems());
        return organismItems;
    }

    public boolean is(Items other) {
        boolean result = (this == other);
        if (this.higherItem != null) {
            result = (result || this.higherItem.is(other));
        }
        return result;
    }

    public boolean isNot(Items other) {
        return !this.is(other);
    }

    public Items getRandomItem() {
        Items randomItem = this;
        if (!randomItem.getLowerItems().isEmpty()) {
            int randomItemIndex = Randomizer.getInt(getLowerItems().size());
            randomItem = lowerItems.get(randomItemIndex).getRandomItem();
        }
        return randomItem;
    }

    public Factory getFactory() {
        return factory;
    }

    private void addToHigherItem() {
        if (this.higherItem != null) {
            this.higherItem.lowerItems.add(this);
        }
    }
}
