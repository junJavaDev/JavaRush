package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Rabbit;
import ru.javarush.ogarkov.island.factory.Factory;
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
