package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Sheep;
import ru.javarush.ogarkov.island.factory.Factory;
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
