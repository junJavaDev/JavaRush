package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.Plant;
import ru.javarush.ogarkov.island.settings.Items;

public class PlantFactory extends AbstractFactory{
    @Override
    public Plant createItem(Items item) {
        count.incrementAndGet();
        return (Plant) createRandomItem(item);
    }
}
