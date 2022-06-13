package ru.javarush.ogarkov.islandsimulation.factory.carnivore;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Animal;
import ru.javarush.ogarkov.islandsimulation.item.carnivore.Eagle;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class EagleFactory implements Factory {
    @Override
    public Animal createItem(Items item) {
        return new Eagle();
    }
}
