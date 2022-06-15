package ru.javarush.ogarkov.island.factory.carnivore;

import ru.javarush.ogarkov.island.entity.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class CarnivoreFactory extends AbstractFactory {
    @Override
    public CarnivoreAnimal createItem() {
        return (CarnivoreAnimal) getRandomFactory(Items.CARNIVORE).createItem();
    }
}
