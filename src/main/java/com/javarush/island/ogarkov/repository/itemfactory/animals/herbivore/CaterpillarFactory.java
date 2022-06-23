package com.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import com.javarush.island.ogarkov.entity.animals.herbivore.Caterpillar;
import com.javarush.island.ogarkov.settings.Items;

public class CaterpillarFactory extends HerbivoreFactory {
    @Override
    public Caterpillar createItem() {
        addCreatedItem(Items.CATERPILLAR);
        return new Caterpillar();
    }
}
