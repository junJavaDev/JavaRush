package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.carnivore.Bear;
import ru.javarush.ogarkov.island.settings.Items;

public class BearFactory extends AbstractFactory {
    @Override
    public Bear createItem(Items item) {
        count.incrementAndGet();
        return new Bear();
    }
}
