package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Flower;
import ru.javarush.ogarkov.island.settings.Items;

public class FlowerFactory extends AbstractFactory {
    @Override
    public Flower createItem(Items item) {
        count.incrementAndGet();
        return new Flower();
    }
}
