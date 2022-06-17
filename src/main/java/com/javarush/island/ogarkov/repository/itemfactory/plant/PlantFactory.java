package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Plant;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.settings.Items;

public class PlantFactory extends AbstractFactory {
    @Override
    public Plant createItem() {
        created.incrementAndGet();
        return (Plant) getRandomFactory(Items.PLANT).createItem();
    }
}
