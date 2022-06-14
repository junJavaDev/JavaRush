package ru.javarush.ogarkov.island.location;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import ru.javarush.ogarkov.island.entity.abstracts.BasicItem;
import ru.javarush.ogarkov.island.settings.Items;
import ru.javarush.ogarkov.island.util.Randomizer;

import static ru.javarush.ogarkov.island.settings.Items.CARNIVORE;
import static ru.javarush.ogarkov.island.settings.Items.HERBIVORE;
import static ru.javarush.ogarkov.island.settings.Setting.*;

// Участок локации, ячейка
public class Territory extends StackPane implements Comparable<Territory> {

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
        fillByAnimals();
    }

    private void initialize() {
        background = createBackground();
        text = new Text();
        imageView = new ImageView();
        getChildren().addAll(background, text, imageView);
        setAlignment(imageView, Pos.TOP_CENTER);
        setAlignment(text, Pos.BOTTOM_CENTER);
        addGrid(xPosition, yPosition, LOCATION_GRID_SIZE);
        setCellColor(Color.LIGHTGREY);
    }

    private Rectangle createBackground() {
        Rectangle cellBackground = new Rectangle();
        cellBackground.setWidth(ISLAND_CELL_WIDTH);
        cellBackground.setHeight(ISLAND_CELL_HEIGHT);
        return cellBackground;
    }

    public void fillByPlants() {
        int amount = 1 + Randomizer.getInt(30);
        population = new BasicItem[amount];
        for (int i = 0; i < amount; i++) {
            population[i] = Items.PLANT.createItem();
        }
    }

    // TODO: 14.06.2022 Времянка
    public void fillByAnimals() {
        int isAnimal = Randomizer.getInt(1000);
        if (isAnimal < 5) {
            int amount = 1 + Randomizer.getInt(5);
            population = new BasicItem[amount];
            for (int i = 0; i < amount; i++) {
                population[i] = CARNIVORE.createItem();
            }
        } else if (isAnimal < 15) {
            int amount = 1 + Randomizer.getInt(5);
            population = new BasicItem[amount];
            for (int i = 0; i < amount; i++) {
                population[i] = HERBIVORE.createItem();
            }
        }

    }

    public void addGrid(int xPosition, int yPosition, int gridSize) {
        setLayoutX((xPosition * (background.getWidth() + gridSize)));
        setLayoutY((yPosition * (background.getHeight() + gridSize)));
    }

    public void addIslandGrid(int xPosition, int yPosition, int gridSize) {
        setLayoutX((xPosition * (ISLAND_CELL_WIDTH + gridSize)));
        setLayoutY((yPosition * (ISLAND_CELL_HEIGHT + gridSize)));
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

    public void setIslandCellColor() {
        Items item = getResident().getItem();
        if (item.is(Items.HERBIVORE)) {
//            setCellColor(Color.BLANCHEDALMOND);
            setCellColor(Color.OLIVEDRAB);
        } else if (item.is(Items.CARNIVORE)) {
            setCellColor(Color.BLACK);
        } else setCellColor(Color.OLIVEDRAB);
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

    @Override
    public int compareTo(Territory secondTerritory) {

        int result = 0;
        Items firstItem = getResident().getItem();
        Items secondItem = secondTerritory.getResident().getItem();

        if (firstItem.is(CARNIVORE) && secondItem.isNot(CARNIVORE)) {
            result = 1;
        } else if (firstItem.isNot(CARNIVORE) && secondItem.is(CARNIVORE)) {
            result = -1;
        } else if (firstItem.is(HERBIVORE) && secondItem.isNot(HERBIVORE)) {
            result = 1;
        } else if (firstItem.isNot(HERBIVORE) && secondItem.is(HERBIVORE)) {
            result = -1;
        }

        if (result == 0) {
            double firstTerritoryWeight = firstItem.getWeight() * getPopulation().length;
            double secondTerritoryWeight = secondItem.getWeight() * secondTerritory.getPopulation().length;
            result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
        }
        return result;
    }
}
