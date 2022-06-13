package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.Landform;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.Plant;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class PlantFactory implements Factory{

    @Override
    public Plant createItem(Items item) {
        return (Plant) createRandomItem(item);
    }
}
