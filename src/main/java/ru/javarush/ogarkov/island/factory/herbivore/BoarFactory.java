package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Boar;
import ru.javarush.ogarkov.island.settings.Items;

public class BoarFactory extends AbstractFactory {
    @Override
    public Boar createItem(Items item) {
        count.incrementAndGet();
        return new Boar();
    }
}
