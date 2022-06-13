package ru.javarush.ogarkov.islandsimulation.item.abstracts;

import javafx.scene.image.Image;
import ru.javarush.ogarkov.islandsimulation.settings.Items;

public abstract class BasicItem {
    protected final Items item = Items.valueOf(getClass().getSimpleName().toUpperCase());
    protected final Image icon = item.getIcon();
    protected final double weight = item.getWeight();
    protected final int maxPerLocation = item.getMaxPerLocation();



    public BasicItem() {
    }

    public Image getIcon() {
        return icon;
    }
}
