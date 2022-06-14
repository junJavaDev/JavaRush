package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Horse;
import ru.javarush.ogarkov.island.settings.Items;

public class HorseFactory extends AbstractFactory {
    @Override
    public Horse createItem(Items item) {
        count.incrementAndGet();
        return new Horse();
    }
}
