package ru.javarush.ogarkov.island.repository.itemfactory.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.carnivore.Boa;
import ru.javarush.ogarkov.island.repository.itemfactory.Factory;
import ru.javarush.ogarkov.island.settings.Items;

public class BoaFactory extends CarnivoreFactory {
    @Override
    public Boa createItem() {
        Factory parentFactory = Items.BOA.getParent().getFactory();
        created.incrementAndGet();
        parentFactory.addCreatedItem();
        return new Boa();
    }
}
