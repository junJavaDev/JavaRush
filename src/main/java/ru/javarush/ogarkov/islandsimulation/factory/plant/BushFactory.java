package ru.javarush.ogarkov.islandsimulation.factory.plant;

import ru.javarush.ogarkov.islandsimulation.factory.Factory;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Plant;
import ru.javarush.ogarkov.islandsimulation.item.plant.Bush;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class BushFactory implements Factory {

    @Override
    public Plant createItem(Items item) {
        return new Bush();
    }
}
