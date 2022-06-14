package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Sheep;
import ru.javarush.ogarkov.island.settings.Items;

public class SheepFactory extends AbstractFactory {
    @Override
    public Sheep createItem(Items item) {
        count.incrementAndGet();
        return new Sheep();
    }
}
