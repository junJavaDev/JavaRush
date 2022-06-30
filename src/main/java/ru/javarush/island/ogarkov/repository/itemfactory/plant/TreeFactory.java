package ru.javarush.island.ogarkov.repository.itemfactory.plant;

import ru.javarush.island.ogarkov.entity.plant.Tree;
import ru.javarush.island.ogarkov.settings.Items;

public class TreeFactory extends PlantFactory {
    @Override
    public Tree createItem() {
        addCreatedItem(Items.TREE);
        return new Tree();
    }
}
