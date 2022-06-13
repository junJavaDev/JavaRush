package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.HerbivoreAnimal;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

import java.util.List;

public class HerbivoreFactory implements Factory{

    private List<Items> children;

    @Override
    public HerbivoreAnimal createItem(Items item) {
        return (HerbivoreAnimal) createRandomItem(item);
    }
}
