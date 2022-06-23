package com.javarush.island.ogarkov.repository.itemfactory.landform;

import com.javarush.island.ogarkov.entity.landform.Landform;
import com.javarush.island.ogarkov.repository.itemfactory.AbstractFactory;
import com.javarush.island.ogarkov.settings.Items;

public class LandformFactory extends AbstractFactory {
    @Override
    public Landform createItem() {
        return (Landform) getRandomFactory(Items.LANDFORM).createItem();
    }
}
