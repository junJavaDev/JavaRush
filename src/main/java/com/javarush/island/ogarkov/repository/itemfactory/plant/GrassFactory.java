package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Grass;
import com.javarush.island.ogarkov.settings.Items;

public class GrassFactory extends PlantFactory {
    @Override
    public Grass createItem() {
        addCreatedItem(Items.GRASS);
        return new Grass();
    }
}
