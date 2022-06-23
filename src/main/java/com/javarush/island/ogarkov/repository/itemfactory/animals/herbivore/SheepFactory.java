package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Sheep;
import com.javarush.island.ogarkov.settings.Items;

public class SheepFactory extends HerbivoreFactory {
    @Override
    public Sheep createItem() {
        addCreatedItem(Items.SHEEP);
        return new Sheep();
    }
}
