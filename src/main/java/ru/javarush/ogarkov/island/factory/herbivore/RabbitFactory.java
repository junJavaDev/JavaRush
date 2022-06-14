package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Rabbit;
import ru.javarush.ogarkov.island.settings.Items;

public class RabbitFactory extends AbstractFactory {
    @Override
    public Rabbit createItem(Items item) {
        count.incrementAndGet();
        return new Rabbit();
    }
}
