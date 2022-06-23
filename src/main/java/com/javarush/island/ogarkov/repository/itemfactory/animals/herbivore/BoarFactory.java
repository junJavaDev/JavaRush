package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Boar;
import com.javarush.island.ogarkov.settings.Items;

public class BoarFactory extends HerbivoreFactory {
    @Override
    public Boar createItem() {
        addCreatedItem(Items.BOAR);
        return new Boar();
    }
}
