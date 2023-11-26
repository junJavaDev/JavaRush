package ru.javarush.island.ogarkov.repository.itemfactory.animals;

import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import ru.javarush.island.ogarkov.settings.Items;

public class AnimalFactory extends AbstractFactory {
    @Override
    public Animal createItem() {
        return (Animal) getRandomFactory(Items.ANIMAL).createItem();
    }
}
