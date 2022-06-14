package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Buffalo;
import ru.javarush.ogarkov.island.settings.Items;

public class BuffaloFactory extends AbstractFactory {
    @Override
    public Buffalo createItem(Items item) {
        count.incrementAndGet();
        return new Buffalo();
    }
}
