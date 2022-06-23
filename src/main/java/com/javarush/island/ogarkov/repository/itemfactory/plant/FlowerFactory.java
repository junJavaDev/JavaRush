package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Flower;
import com.javarush.island.ogarkov.settings.Items;

public class FlowerFactory extends PlantFactory {
    @Override
    public Flower createItem() {
        addCreatedItem(Items.FLOWER);
        return new Flower();
    }
}
