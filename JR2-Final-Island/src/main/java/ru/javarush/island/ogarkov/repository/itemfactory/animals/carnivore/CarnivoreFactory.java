package ru.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import ru.javarush.island.ogarkov.entity.animals.carnivore.CarnivoreAnimal;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.island.ogarkov.settings.Items;

public class CarnivoreFactory extends AnimalFactory {
    @Override
    public CarnivoreAnimal createItem() {
        return (CarnivoreAnimal) getRandomFactory(Items.CARNIVORE).createItem();
    }
}
