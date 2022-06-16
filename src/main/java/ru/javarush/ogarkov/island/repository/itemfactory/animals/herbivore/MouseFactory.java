package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.animals.herbivore.Mouse;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class MouseFactory extends AbstractFactory {
    @Override
    public Mouse createItem() {
        Factory parentFactory = Items.MOUSE.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Mouse();
    }
}
