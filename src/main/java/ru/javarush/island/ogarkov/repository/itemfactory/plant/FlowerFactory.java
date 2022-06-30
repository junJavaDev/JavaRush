package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Flower;
import ru.javarush.island.ogarkov.settings.Items;

public class FlowerFactory extends PlantFactory {
    @Override
    public Flower createItem() {
        addCreatedItem(Items.FLOWER);
        return new Flower();
    }
}
