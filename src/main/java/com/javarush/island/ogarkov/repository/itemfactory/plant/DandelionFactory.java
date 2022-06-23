package com.javarush.island.ogarkov.repository.itemfactory.plant;

import com.javarush.island.ogarkov.entity.plant.Dandelion;
import com.javarush.island.ogarkov.settings.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class DandelionFactory extends PlantFactory {
    @Override
    public Dandelion createItem() {
        addCreatedItem(Items.DANDELION);
        return new Dandelion();
    }
}
