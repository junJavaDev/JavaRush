package ru.javarush.ogarkov.islandsimulation.location;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.settings.Items;
import ru.javarush.ogarkov.islandsimulation.settings.Utils;

import static ru.javarush.ogarkov.islandsimulation.settings.Setting.*;

// Участок локации, ячейка
public class Territory extends StackPane{

    private Rectangle background;
    private Text text;
    private ImageView imageView;
    public int xPosition;
    public int yPosition;
    public final Location location;
    private BasicItem[] population;

    public Territory(Location location, int xPosition, int yPosition) {
        this.location = location;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initialize();
        fillByPlants();
    }

    private void initialize(){
        background = createBackground();
        text = new Text();
        imageView = new ImageView();
        getChildren().addAll(background, text, imageView);
        setAlignment(imageView, Pos.TOP_CENTER);
        setAlignment(text, Pos.BOTTOM_CENTER);
        addGrid(xPosition, yPosition, LOCATION_GRID_SIZE);
        setCellColor(Color.LIGHTGREY);
    }
    private Rectangle createBackground(){
        Rectangle cellBackground = new Rectangle();
        cellBackground.setWidth(LOCATION_CELL_WIDTH);
        cellBackground.setHeight(LOCATION_CELL_HEIGHT);
        return cellBackground;
    }

    public void fillByPlants() {
        int amount = 1 + Utils.getRandomInt(30);
        population = new BasicItem[amount];
        for (int i = 0; i < amount; i++) {
            population[i] = Items.PLANT.createItem();
        }
    }
    public void addGrid(int xPosition, int yPosition, int gridSize) {
        setLayoutX((xPosition * (background.getWidth() + gridSize)));
        setLayoutY((yPosition * (background.getHeight() + gridSize)));
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setCellImage(Image image) {
        imageView.setImage(image);
    }

    public Rectangle getCellBackground() {
        return background;
    }

    public Text getText() {
        return text;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setCellColor(Color color) {
        background.setFill(color);
    }

    public BasicItem[] getPopulation() {
        return population;
    }

    public void clearPopulation() {
        population = null;
    }

    public BasicItem getResident() {
        return population[0];
    }

    public Image getIcon() {
        return getResident().getIcon();
    }

}
