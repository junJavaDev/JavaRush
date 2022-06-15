package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Caterpillar;
import ru.javarush.ogarkov.island.factory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class CaterpillarFactory extends AbstractFactory {
    @Override
    public Caterpillar createItem() {
        Factory parentFactory = Items.CATERPILLAR.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Caterpillar();
    }
}
