package ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.carnivore.Wolf;
import ru.javarush.island.ogarkov.settings.Items;

public class WolfFactory extends CarnivoreFactory {
    @Override
    public Wolf createItem() {
        addCreatedItem(Items.WOLF);
        return new Wolf();
    }
}
