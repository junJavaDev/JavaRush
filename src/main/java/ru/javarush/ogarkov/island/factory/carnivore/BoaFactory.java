package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.entity.carnivore.Boa;
import ru.javarush.ogarkov.island.factory.Factory;
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
