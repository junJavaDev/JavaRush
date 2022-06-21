package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.settings.Items;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.javarush.island.ogarkov.settings.Items.CARNIVORE;
import static com.javarush.island.ogarkov.settings.Setting.*;

// Участок локации, ячейка
public class Cell extends StackPane {
    private final Lock lock = new ReentrantLock(true);
    private Rectangle background;
    private Text quantity;
    private ImageView imageView;
    public Territory territory;
    private final int territoryRow;
    private final int territoryCell;
    private Set<Organism> population;
    private Items residentItem;

    public Cell(int territoryRow, int territoryCell, Territory territory) {
        this.territoryRow = territoryRow;
        this.territoryCell = territoryCell;
        this.territory = territory;
        init();
    }

    private void init() {
        background = createBackground();
        quantity = new Text();
        imageView = new ImageView();
        getChildren().addAll(background, quantity, imageView);
        setAlignment(imageView, Pos.TOP_CENTER);
        setAlignment(quantity, Pos.BOTTOM_CENTER);
        addIslandGrid(territoryCell, territoryRow, ISLAND_GRID_SIZE);
        setCellColor(Color.TRANSPARENT);
    }

    private Rectangle createBackground() {
        Rectangle cellBackground = new Rectangle();
        cellBackground.setWidth(ISLAND_CELL_WIDTH);
        cellBackground.setHeight(ISLAND_CELL_HEIGHT);
        return cellBackground;
    }

    public void addGrid(int row, int col, int gridSize) {
        setLayoutX((col * (background.getWidth() + gridSize)));
        setLayoutY((row * (background.getHeight() + gridSize)));
    }

    public void addIslandGrid(int row, int col, int gridSize) {
        setLayoutX((col * (ISLAND_CELL_WIDTH + gridSize)));
        setLayoutY((row * (ISLAND_CELL_HEIGHT + gridSize)));
    }


    public void setCellImage(Image image) {
        imageView.setImage(image);
    }

    public Rectangle getCellBackground() {
        return background;
    }

    public void setCellColor(Color color) {
        background.setFill(color);
    }

    public void setPopulation(Set<Organism> population) {
        this.population = population;
    }

    public void setLeaderColor() {
        Items item = getResidentItem();
        if (item.is(CARNIVORE)) {
            setCellColor(Color.BLACK);
        } else setCellColor(Color.OLIVEDRAB);
    }

    public Set<Organism> getPopulation() {
        return population;
    }

    public Items getResidentItem() {
        return residentItem;
    }

    public Image getIcon() {
        return getResidentItem().getIcon();
    }

    public int getTerritoryRow() {
        return territoryRow;
    }

    public int getTerritoryCell() {
        return territoryCell;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setQuantity(String newQuantity) {
        quantity.setText(newQuantity);
    }

    public void setResidentItem(Items residentItem) {
        this.residentItem = residentItem;
    }
}
