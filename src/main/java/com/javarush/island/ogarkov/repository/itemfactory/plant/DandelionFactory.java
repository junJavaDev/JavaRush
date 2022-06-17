package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Dandelion;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class DandelionFactory extends AbstractFactory {
    public final AtomicInteger count = new AtomicInteger();
    @Override
    public Dandelion createItem() {
        Factory parentFactory = Items.DANDELION.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Dandelion();
    }
}
