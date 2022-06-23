package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Goat;
import com.javarush.island.ogarkov.settings.Items;

public class GoatFactory extends HerbivoreFactory {
    @Override
    public Goat createItem() {
        addCreatedItem(Items.GOAT);
        return new Goat();
    }
}
