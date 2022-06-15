package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Horse;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class HorseFactory extends AbstractFactory {
    @Override
    public Horse createItem() {
        Factory parentFactory = Items.HORSE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Horse();
    }
}
