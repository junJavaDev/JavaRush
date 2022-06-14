package ru.javarush.ogarkov.island.entity.abstracts;

import javafx.scene.image.Image;
import ru.javarush.ogarkov.island.entity.landform.Plain;
import ru.javarush.ogarkov.island.settings.Items;

public abstract class BasicItem {
    public Items getItem() {
        return item;
    }

    protected String name;
    protected final Items item = Items.valueOf(getClass().getSimpleName().toUpperCase());
    protected final Image icon = item.getIcon();
    protected final double weight = item.getWeight();
    protected final int maxPerLocation = item.getMaxPerLocation();

    public BasicItem() {
    }

    public BasicItem die() {
        System.out.println("умирает (от голода или съедено)");
        return new Plain();
    }

    public Image getIcon() {
        return icon;
    }
}
