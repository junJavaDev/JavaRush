package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Deer;
import com.javarush.island.ogarkov.settings.Items;

public class DeerFactory extends HerbivoreFactory {
    @Override
    public Deer createItem() {
        addCreatedItem(Items.DEER);
        return new Deer();
    }
}
