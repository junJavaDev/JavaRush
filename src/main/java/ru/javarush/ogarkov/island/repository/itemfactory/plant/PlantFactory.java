package ru.javarush.ogarkov.island.repository.itemfactory.plant;

import ru.javarush.ogarkov.island.entity.plant.Plant;
import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class PlantFactory extends AbstractFactory {
    @Override
    public Plant createItem() {
        created.incrementAndGet();
        return (Plant) getRandomFactory(Items.PLANT).createItem();
    }
}
