package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Horse;
import ru.javarush.island.ogarkov.settings.Items;

public class HorseFactory extends HerbivoreFactory {
    @Override
    public Horse createItem() {
        addCreatedItem(Items.HORSE);
        return new Horse();
    }
}
