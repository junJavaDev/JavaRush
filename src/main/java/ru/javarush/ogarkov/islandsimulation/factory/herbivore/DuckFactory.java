package ru.javarush.ogarkov.islandsimulation.factory.herbivore;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Animal;
import ru.javarush.ogarkov.islandsimulation.item.herbivore.Duck;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class DuckFactory implements Factory {
    @Override
    public Animal createItem(Items item) {
        return new Duck();
    }
}
