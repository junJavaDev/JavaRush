package ru.javarush.island.ogarkov.repository.itemfactory.landform;

import ru.javarush.island.ogarkov.entity.landform.Landform;
import ru.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import ru.javarush.island.ogarkov.settings.Items;

public class LandformFactory extends AbstractFactory {
    @Override
    public Landform createItem() {
        return (Landform) getRandomFactory(Items.LANDFORM).createItem();
    }
}
