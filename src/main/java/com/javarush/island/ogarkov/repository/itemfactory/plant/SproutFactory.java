package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Sprout;
import com.javarush.island.ogarkov.settings.Items;

public class SproutFactory extends PlantFactory {
    @Override
    public Sprout createItem() {
        addCreatedItem(Items.SPROUT);
        return new Sprout();
    }
}
