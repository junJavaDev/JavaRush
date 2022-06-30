package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Boar;
import ru.javarush.island.ogarkov.settings.Items;

public class BoarFactory extends HerbivoreFactory {
    @Override
    public Boar createItem() {
        addCreatedItem(Items.BOAR);
        return new Boar();
    }
}
