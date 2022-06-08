package ru.javarush.ogarkov.islandsimulation.item.abstracts;

import javafx.scene.image.Image;
import ru.javarush.ogarkov.islandsimulation.item.Items;

public abstract class BasicItem {
    protected Image icon;

    public BasicItem() {
        this.icon = Items.valueOf(getClass().getSimpleName().toUpperCase()).getIcon();
    }

    public Image getIcon() {
        return icon;
    }

}
