package ru.javarush.ogarkov.island.repository.itemfactory.animals.herbivore;

import ru.javarush.ogarkov.island.entity.animals.herbivore.HerbivoreAnimal;
import ru.javarush.ogarkov.island.repository.itemfactory.animals.AnimalFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class HerbivoreFactory extends AnimalFactory {
    @Override
    public HerbivoreAnimal createItem() {
        created.incrementAndGet();
        return (HerbivoreAnimal) getRandomFactory(Items.HERBIVORE).createItem();
    }
}
