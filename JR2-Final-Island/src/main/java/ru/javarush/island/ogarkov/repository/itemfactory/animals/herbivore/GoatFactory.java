package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Goat;
import ru.javarush.island.ogarkov.settings.Items;

public class GoatFactory extends HerbivoreFactory {
    @Override
    public Goat createItem() {
        addCreatedItem(Items.GOAT);
        return new Goat();
    }
}
