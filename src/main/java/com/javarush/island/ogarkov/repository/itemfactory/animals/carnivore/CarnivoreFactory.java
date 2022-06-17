package com.javarush.island.ogarkov.repository.itemfactory.animals.carnivore;

import com.javarush.island.ogarkov.entity.animals.carnivore.CarnivoreAnimal;
import com.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import com.javarush.island.ogarkov.settings.Items;

public class CarnivoreFactory extends AnimalFactory {
    @Override
    public CarnivoreAnimal createItem() {
        return (CarnivoreAnimal) getRandomFactory(Items.CARNIVORE).createItem();
    }
}
