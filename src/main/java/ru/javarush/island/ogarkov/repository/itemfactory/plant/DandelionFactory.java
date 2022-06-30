package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Dandelion;
import ru.javarush.island.ogarkov.settings.Items;

public class DandelionFactory extends PlantFactory {
    @Override
    public Dandelion createItem() {
        addCreatedItem(Items.DANDELION);
        return new Dandelion();
    }
}
