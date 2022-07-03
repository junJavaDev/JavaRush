package ru.javarush.island.ogarkov.view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static ru.javarush.island.ogarkov.settings.Setting.*;

public class CellView extends StackPane {
    private Rectangle background;
    private Text itemsCount;
    private ImageView icon;
    private int index;

    public CellView() {
        init();
    }

    public void setImage(Image image) {
        icon.setImage(image);
    }

    public void setColor(Color color) {
        background.setFill(color);
    }

    public void setItemsCount(String itemsCount) {
        this.itemsCount.setText(itemsCount);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setIslandLayout(int row, int col) {
        setBackgroundSize(ISLAND_CELL_WIDTH, ISLAND_CELL_HEIGHT);
        setLayoutX((col * (ISLAND_CELL_WIDTH + ISLAND_GRID_SIZE)));
        setLayoutY((row * (ISLAND_CELL_HEIGHT + ISLAND_GRID_SIZE)));
        setColor(DEFAULT_ISLAND_COLOR);
    }

    public void setTerritoryLayout(int row, int col) {
        setBackgroundSize(TERRITORY_CELL_WIDTH, TERRITORY_CELL_HEIGHT);
        setLayoutX((col * (TERRITORY_CELL_WIDTH + TERRITORY_GRID_SIZE)));
        setLayoutY((row * (TERRITORY_CELL_HEIGHT + TERRITORY_GRID_SIZE)));
        setColor(DEFAULT_TERRITORY_COLOR);
    }

    public void updateView(Image image, Color color) {
        setImage(image);
        setColor(color);
    }

    public void updateView(Image image, Color color, String text) {
        updateView(image, color);
        setItemsCount(text);
    }

    private void init() {
        background = new Rectangle();
        icon = new ImageView();
        itemsCount = new Text();
        getChildren().addAll(background, icon, itemsCount);
        setAlignment(icon, Pos.TOP_CENTER);
        setAlignment(itemsCount, Pos.BOTTOM_CENTER);
    }

    private void setBackgroundSize(double width, double height) {
        background.setWidth(width);
        background.setHeight(height);
    }
}
