package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.HerbivoreAnimal;
import ru.javarush.island.ogarkov.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.island.ogarkov.settings.Items;

public class HerbivoreFactory extends AnimalFactory {
    @Override
    public HerbivoreAnimal createItem() {
        return (HerbivoreAnimal) getRandomFactory(Items.HERBIVORE).createItem();
    }
}
