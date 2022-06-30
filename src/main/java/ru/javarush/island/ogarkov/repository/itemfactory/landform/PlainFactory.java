package ru.javarush.island.ogarkov.repository.itemfactory.landform;

import ru.javarush.island.ogarkov.entity.landform.Plain;
import ru.javarush.island.ogarkov.settings.Items;

public class PlainFactory extends LandformFactory {
    @Override
    public Plain createItem() {
        addCreatedItem(Items.PLAIN);
        return new Plain();
    }
}

