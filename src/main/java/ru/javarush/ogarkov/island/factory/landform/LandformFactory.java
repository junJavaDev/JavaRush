package ru.javarush.ogarkov.island.factory.landform;

import ru.javarush.ogarkov.island.entity.abstracts.Landform;
import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.settings.Items;

public class LandformFactory extends AbstractFactory {
    @Override
    public Landform createItem() {
        created.incrementAndGet();
        return (Landform) getRandomFactory(Items.LANDFORM).createItem();
    }
}
