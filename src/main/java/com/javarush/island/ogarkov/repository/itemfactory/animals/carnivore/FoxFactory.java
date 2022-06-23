package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.Fox;
import com.javarush.island.ogarkov.settings.Items;

public class FoxFactory extends CarnivoreFactory {
    @Override
    public Fox createItem() {
        addCreatedItem(Items.FOX);
        return new Fox();
    }
}
