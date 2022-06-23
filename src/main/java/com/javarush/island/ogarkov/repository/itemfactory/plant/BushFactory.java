package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Bush;
import com.javarush.island.ogarkov.settings.Items;

public class BushFactory extends PlantFactory {
    @Override
    public Bush createItem() {
        addCreatedItem(Items.BUSH);
        return new Bush();
    }
}
