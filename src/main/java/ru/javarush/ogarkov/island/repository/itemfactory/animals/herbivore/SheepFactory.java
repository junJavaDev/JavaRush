package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Sheep;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class SheepFactory extends AbstractFactory {
    @Override
    public Sheep createItem() {
        Factory parentFactory = Items.SHEEP.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Sheep();
    }
}
