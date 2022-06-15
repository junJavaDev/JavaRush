package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Boar;
import ru.javarush.ogarkov.island.factory.Factory;
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
