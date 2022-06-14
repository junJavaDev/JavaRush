package ru.javarush.ogarkov.island.factory.herbivore;

import ru.javarush.ogarkov.island.factory.AbstractFactory;
import ru.javarush.ogarkov.island.entity.herbivore.Mouse;
import ru.javarush.ogarkov.island.settings.Items;

public class MouseFactory extends AbstractFactory {
    @Override
    public Mouse createItem(Items item) {
        count.incrementAndGet();
        return new Mouse();
    }
}
