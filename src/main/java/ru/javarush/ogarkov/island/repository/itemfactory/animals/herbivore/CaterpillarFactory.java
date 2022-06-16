package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Caterpillar;
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
