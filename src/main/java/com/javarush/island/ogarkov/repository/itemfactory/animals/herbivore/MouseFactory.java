package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Mouse;
import com.javarush.island.ogarkov.settings.Items;

public class MouseFactory extends HerbivoreFactory {
    @Override
    public Mouse createItem() {
        addCreatedItem(Items.MOUSE);
        return new Mouse();
    }
}
