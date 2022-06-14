package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.HerbivoreAnimal;
import ru.javarush.ogarkov.island.settings.Items;

public class HerbivoreFactory extends AbstractFactory{
    @Override
    public HerbivoreAnimal createItem(Items item) {
        count.incrementAndGet();
        return (HerbivoreAnimal)createRandomItem(item);
    }
}
