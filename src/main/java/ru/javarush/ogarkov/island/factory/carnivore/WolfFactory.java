package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.carnivore.Wolf;
import ru.javarush.ogarkov.island.settings.Items;

public class WolfFactory extends AbstractFactory {
    @Override
    public Wolf createItem(Items item) {
        count.incrementAndGet();
        return new Wolf();
    }
}
