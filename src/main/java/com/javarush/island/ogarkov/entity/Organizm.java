package com.javarush.island.ogarkov.entity;

import javafx.scene.image.Image;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.settings.Items;

public abstract class Organizm {
    public Items getItem() {
        return item;
    }

    protected String name;
    protected final Items item;
    protected final Image icon;
    protected final double weight;
    protected final int maxPerLocation;
    protected Cell cell;

    public Organizm() {
        item = Items.valueOf(getClass().getSimpleName().toUpperCase());
        icon = item.getIcon();
        weight = item.getWeight();
        maxPerLocation = item.getMaxPerLocation();
        name = item.getName();
    }

    public Organizm terminated() {
        System.out.println("умирает (от голода или съедено)");
        return Items.LANDFORM.getFactory().createItem();
    }

    public Image getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public Cell getCell() {
        return cell;
    }

    public void setTerritory(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Organizm{" +
                "name='" + name + '\'' +
                '}';
    }
}
