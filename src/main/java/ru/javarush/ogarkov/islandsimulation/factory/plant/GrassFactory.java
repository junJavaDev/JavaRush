package ru.javarush.ogarkov.islandsimulation.factory.plant;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Plant;
import ru.javarush.ogarkov.islandsimulation.item.plant.Grass;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class GrassFactory implements Factory {
    @Override
    public Plant createItem(Items item) {
        return new Grass();
    }
}
