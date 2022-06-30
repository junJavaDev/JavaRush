package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Sheep;
import ru.javarush.island.ogarkov.settings.Items;

public class SheepFactory extends HerbivoreFactory {
    @Override
    public Sheep createItem() {
        addCreatedItem(Items.SHEEP);
        return new Sheep();
    }
}
