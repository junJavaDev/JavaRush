package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.plant.Tree;
import ru.javarush.ogarkov.island.settings.Items;

public class TreeFactory extends AbstractFactory {
    @Override
    public Tree createItem() {
        Factory parentFactory = Items.TREE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Tree();
    }
}
