package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Duck;
import ru.javarush.island.ogarkov.settings.Items;

public class DuckFactory extends HerbivoreFactory {
    @Override
    public Duck createItem() {
        addCreatedItem(Items.DUCK);
        return new Duck();
    }
}
