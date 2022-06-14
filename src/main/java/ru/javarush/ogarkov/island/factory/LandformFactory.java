package ru.javarush.ogarkov.island.factory;

import ru.javarush.ogarkov.island.entity.abstracts.Landform;
import ru.javarush.ogarkov.island.settings.Items;

public class LandformFactory extends AbstractFactory{
    @Override
    public Landform createItem(Items item) {
        count.incrementAndGet();
        return (Landform) createRandomItem(item);
    }
}
