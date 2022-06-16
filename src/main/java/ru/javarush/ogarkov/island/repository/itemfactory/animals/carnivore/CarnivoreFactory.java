package ru.javarush.ogarkov.island.repository.itemfactory.animals.carnivore;

import ru.javarush.ogarkov.island.entity.animals.carnivore.CarnivoreAnimal;
import ru.javarush.ogarkov.island.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class CarnivoreFactory extends AnimalFactory {
    @Override
    public CarnivoreAnimal createItem() {
        return (CarnivoreAnimal) getRandomFactory(Items.CARNIVORE).createItem();
    }
}
