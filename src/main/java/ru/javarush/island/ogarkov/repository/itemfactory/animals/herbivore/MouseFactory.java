package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Mouse;
import ru.javarush.island.ogarkov.settings.Items;

public class MouseFactory extends HerbivoreFactory {
    @Override
    public Mouse createItem() {
        addCreatedItem(Items.MOUSE);
        return new Mouse();
    }
}
