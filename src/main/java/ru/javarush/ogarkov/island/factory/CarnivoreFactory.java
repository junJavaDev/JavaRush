package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.island.settings.Items;

public class CarnivoreFactory extends AbstractFactory{
    @Override
    public CarnivoreAnimal createItem(Items item) {
        count.incrementAndGet();
        return (CarnivoreAnimal) createRandomItem(item);
    }
}
