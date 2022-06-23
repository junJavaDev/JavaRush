package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Horse;
import com.javarush.island.ogarkov.settings.Items;

public class HorseFactory extends HerbivoreFactory {
    @Override
    public Horse createItem() {
        addCreatedItem(Items.HORSE);
        return new Horse();
    }
}
