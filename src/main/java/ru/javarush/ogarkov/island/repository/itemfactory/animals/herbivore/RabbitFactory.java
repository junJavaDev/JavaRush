package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Rabbit;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class RabbitFactory extends AbstractFactory {
    @Override
    public Rabbit createItem() {
        Factory parentFactory = Items.RABBIT.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Rabbit();
    }
}
