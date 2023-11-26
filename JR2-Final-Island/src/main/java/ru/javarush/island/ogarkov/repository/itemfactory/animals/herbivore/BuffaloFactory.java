package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Buffalo;
import ru.javarush.island.ogarkov.settings.Items;

public class BuffaloFactory extends HerbivoreFactory {
    @Override
    public Buffalo createItem() {
        addCreatedItem(Items.BUFFALO);
        return new Buffalo();
    }
}
