package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.HerbivoreAnimal;
import com.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import com.javarush.island.ogarkov.settings.Items;

public class HerbivoreFactory extends AnimalFactory {
    @Override
    public HerbivoreAnimal createItem() {
        return (HerbivoreAnimal) getRandomFactory(Items.HERBIVORE).createItem();
    }
}
