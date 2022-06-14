package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.plant.Tree;
import ru.javarush.ogarkov.island.settings.Items;

public class TreeFactory extends AbstractFactory {
    @Override
    public Tree createItem(Items item) {
        count.incrementAndGet();
        return new Tree();
    }
}
