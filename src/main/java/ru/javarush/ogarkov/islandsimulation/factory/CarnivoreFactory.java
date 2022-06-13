package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.CarnivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class CarnivoreFactory implements Factory{

    @Override
    public CarnivoreAnimal createItem(Items item) {
        return (CarnivoreAnimal) createRandomItem(item);
    }
}
