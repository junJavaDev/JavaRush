package ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.carnivore.Fox;
import ru.javarush.island.ogarkov.settings.Items;

public class FoxFactory extends CarnivoreFactory {
    @Override
    public Fox createItem() {
        addCreatedItem(Items.FOX);
        return new Fox();
    }
}
