package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Rabbit;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.repository.itemfactory.Factory;
import com.javarush.island.ogarkov.settings.Items;

public class RabbitFactory extends AbstractFactory {
    @Override
    public Rabbit createItem() {
        Factory parentFactory = Items.RABBIT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Rabbit();
    }
}
