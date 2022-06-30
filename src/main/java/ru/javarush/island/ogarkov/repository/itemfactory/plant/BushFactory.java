package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Bush;
import ru.javarush.island.ogarkov.settings.Items;

public class BushFactory extends PlantFactory {
    @Override
    public Bush createItem() {
        addCreatedItem(Items.BUSH);
        return new Bush();
    }
}
