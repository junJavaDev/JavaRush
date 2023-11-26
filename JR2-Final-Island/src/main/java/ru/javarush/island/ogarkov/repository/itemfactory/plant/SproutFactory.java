package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Sprout;
import ru.javarush.island.ogarkov.settings.Items;

public class SproutFactory extends PlantFactory {
    @Override
    public Sprout createItem() {
        addCreatedItem(Items.SPROUT);
        return new Sprout();
    }
}
