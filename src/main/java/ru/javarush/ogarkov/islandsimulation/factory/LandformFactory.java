package ru.javarush.ogarkov.islandsimulation.factory;

import ru.javarush.ogarkov.islandsimulation.item.abstracts.Landform;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public class LandformFactory implements Factory{
    @Override
    public Landform createItem(Items item) {
        return (Landform) createRandomItem(item);
    }
}
