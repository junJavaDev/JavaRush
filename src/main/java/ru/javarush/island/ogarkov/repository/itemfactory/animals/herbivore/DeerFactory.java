package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Deer;
import ru.javarush.island.ogarkov.settings.Items;

public class DeerFactory extends HerbivoreFactory {
    @Override
    public Deer createItem() {
        addCreatedItem(Items.DEER);
        return new Deer();
    }
}
