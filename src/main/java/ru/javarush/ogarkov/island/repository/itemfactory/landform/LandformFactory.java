package ru.javarush.ogarkov.island.repository.itemfactory.landform;

import ru.javarush.ogarkov.island.entity.landform.Landform;
import ru.javarush.ogarkov.island.repository.itemfactory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class LandformFactory extends AbstractFactory {
    @Override
    public Landform createItem() {
        created.incrementAndGet();
        return (Landform) getRandomFactory(Items.LANDFORM).createItem();
    }
}
