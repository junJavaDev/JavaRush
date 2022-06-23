package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Duck;
import com.javarush.island.ogarkov.settings.Items;

public class DuckFactory extends HerbivoreFactory {
    @Override
    public Duck createItem() {
        addCreatedItem(Items.DUCK);
        return new Duck();
    }
}
