package ru.javarush.ogarkov.islandsimulation.factory.plant;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Plant;
import ru.javarush.ogarkov.islandsimulation.item.plant.Sprout;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class SproutFactory implements Factory {
    @Override
    public Plant createItem(Items item) {
        return new Sprout();
    }
}
