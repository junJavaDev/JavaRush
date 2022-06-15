package ru.javarush.ogarkov.island.factory.plant;

import ru.javarush.ogarkov.island.entity.abstracts.Plant;
import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class PlantFactory extends AbstractFactory {
    @Override
    public Plant createItem() {
        created.incrementAndGet();
        return (Plant) getRandomFactory(Items.PLANT).createItem();
    }
}
