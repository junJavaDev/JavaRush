package ru.javarush.island.ogarkov.view.javafx;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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

    public void setLayout(int row, int col, int width, int height, int islandGridSize, Color color) {
        setBackgroundSize(width, height);
        setLayoutX((col * (width + islandGridSize)));
        setLayoutY((row * (height + islandGridSize)));
        setColor(color);
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
