package ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.carnivore.Bear;
import ru.javarush.island.ogarkov.settings.Items;

public class BearFactory extends CarnivoreFactory {
    @Override
    public Bear createItem() {
        addCreatedItem(Items.BEAR);
        return new Bear();
    }
}
