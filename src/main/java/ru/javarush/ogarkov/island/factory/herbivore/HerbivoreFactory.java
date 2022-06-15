package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.entity.abstracts.HerbivoreAnimal;
import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class HerbivoreFactory extends AbstractFactory {
    @Override
    public HerbivoreAnimal createItem() {
        created.incrementAndGet();
        return (HerbivoreAnimal) getRandomFactory(Items.HERBIVORE).createItem();
    }
}
