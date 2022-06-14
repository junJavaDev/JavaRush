package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.carnivore.Fox;
import ru.javarush.ogarkov.island.settings.Items;

public class FoxFactory extends AbstractFactory {
    @Override
    public Fox createItem(Items item) {
        count.incrementAndGet();
        return new Fox();
    }
}
