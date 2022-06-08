package ru.javarush.ogarkov.islandsimulation;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;


public class Location extends StackPane{

    private final Rectangle cellBackground;
    private Text text;
    private ImageView imageView;
    private int x;
    private int y;


    public Location(Rectangle rectangle, Text text, ImageView imageView) {
        super(rectangle, text, imageView);
        this.cellBackground = rectangle;
        this.text = text;
        this.imageView = imageView;
    }

    public void setPositionX(int x) {
        this.x = x;
        setLayoutX((x * (cellBackground.getWidth() + 1)));
    }

    public void setPositionY(int y) {
        this.y = y;
        setLayoutY((y * (cellBackground.getHeight() + 1)));
    }

    public void setCellText(String text) {
        this.text.setText(text);
    }

    public void setCellImage(Image image) {
        imageView.setImage(image);
    }

    public Rectangle getCellBackground() {
        return cellBackground;
    }

    public void setCellColor(Color color) {
        cellBackground.setFill(color);
    }

}
