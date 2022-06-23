package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Buffalo;
import com.javarush.island.ogarkov.settings.Items;

public class BuffaloFactory extends HerbivoreFactory {
    @Override
    public Buffalo createItem() {
        addCreatedItem(Items.BUFFALO);
        return new Buffalo();
    }
}
