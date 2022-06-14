package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.carnivore.Boa;
import ru.javarush.ogarkov.island.settings.Items;

public class BoaFactory extends AbstractFactory {
    @Override
    public Boa createItem(Items item) {
        count.incrementAndGet();
        return new Boa();
    }
}
