package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Grass;
import ru.javarush.island.ogarkov.settings.Items;

public class GrassFactory extends PlantFactory {
    @Override
    public Grass createItem() {
        addCreatedItem(Items.GRASS);
        return new Grass();
    }
}
