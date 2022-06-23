package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Boa;
import com.javarush.island.ogarkov.settings.Items;

public class BoaFactory extends CarnivoreFactory {
    @Override
    public Boa createItem() {
        addCreatedItem(Items.BOA);
        return new Boa();
    }
}
