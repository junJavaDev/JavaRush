package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Dandelion;
import ru.javarush.ogarkov.island.settings.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class DandelionFactory extends AbstractFactory {
    public final AtomicInteger count = new AtomicInteger();
    @Override
    public Dandelion createItem(Items item) {
        count.incrementAndGet();
        return new Dandelion();
    }
}
