package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Boar;
import ru.javarush.ogarkov.island.settings.Items;

public class BoarFactory extends AbstractFactory {
    @Override
    public Boar createItem() {
        Factory parentFactory = Items.BOAR.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Boar();
    }
}
