package ru.javarush.island.ogarkov.repository.itemfactory.animals.herbivore;

import ru.javarush.island.ogarkov.entity.animals.herbivore.Rabbit;
import ru.javarush.island.ogarkov.settings.Items;

public class RabbitFactory extends HerbivoreFactory {
    @Override
    public Rabbit createItem() {
        addCreatedItem(Items.RABBIT);
        return new Rabbit();
    }
}
