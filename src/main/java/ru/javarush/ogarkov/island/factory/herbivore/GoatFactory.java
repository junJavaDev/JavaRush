package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Goat;
import ru.javarush.ogarkov.island.settings.Items;

public class GoatFactory extends AbstractFactory {
    @Override
    public Goat createItem(Items item) {
        count.incrementAndGet();
        return new Goat();
    }
}
