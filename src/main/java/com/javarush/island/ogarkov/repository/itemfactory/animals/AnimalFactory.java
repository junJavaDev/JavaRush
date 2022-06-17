package com.javarush.island.ogarkov.repository.itemfactory.animals;

import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.settings.Items;

public class AnimalFactory extends AbstractFactory {
    @Override
    public Animal createItem() {
        created.incrementAndGet();
        return (Animal) getRandomFactory(Items.ANIMAL).createItem();
    }
}
