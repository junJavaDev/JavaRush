package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Tree;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class TreeFactory extends AbstractFactory {
    @Override
    public Tree createItem() {
        Factory parentFactory = Items.TREE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Tree();
    }
}
