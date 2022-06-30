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
    private Text text;
    private ImageView imageView;
    private int index;

    public CellView() {
        init();
    }

    private void init() {
        background = new Rectangle();
        imageView = new ImageView();
        text = new Text();
        getChildren().addAll(background, imageView, text);
        setAlignment(imageView, Pos.TOP_CENTER);
        setAlignment(text, Pos.BOTTOM_CENTER);
    }

    private void setBackgroundSize(double width, double height) {
        background.setWidth(width);
        background.setHeight(height);
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
        setText(text);
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void setColor(Color color) {
        background.setFill(color);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
