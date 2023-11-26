package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Plant;
import ru.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import ru.javarush.island.ogarkov.settings.Items;

public class PlantFactory extends AbstractFactory {
    @Override
    public Plant createItem() {
        return (Plant) getRandomFactory(Items.PLANT).createItem();
    }
}
