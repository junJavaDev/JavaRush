package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Flower;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class FlowerFactory extends AbstractFactory {
    @Override
    public Flower createItem() {
        Factory parentFactory = Items.FLOWER.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Flower();
    }
}
