package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Duck;
import ru.javarush.ogarkov.island.settings.Items;

public class DuckFactory extends AbstractFactory {
    @Override
    public Duck createItem(Items item) {
        count.incrementAndGet();
        return new Duck();
    }
}
