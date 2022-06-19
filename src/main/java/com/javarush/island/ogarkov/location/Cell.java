package com.javarush.island.ogarkov.location;

import com.javarush.island.ogarkov.entity.Organizm;
import com.javarush.island.ogarkov.util.Randomizer;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import com.javarush.island.ogarkov.settings.Items;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static com.javarush.island.ogarkov.settings.Items.*;
import static com.javarush.island.ogarkov.settings.Setting.*;

// Участок локации, ячейка
public class Cell extends StackPane implements Comparable<Cell> {

    private final static AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());

    private final long id = idCounter.incrementAndGet();
    private Rectangle background;
    private Text text;
    private ImageView imageView;
    private final int xPosition;
    private final int yPosition;
    public Territory territory;
    private final Set<Organizm> population;

    public Cell(int xPosition, int yPosition, Territory territory) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.territory = territory;
        population = new HashSet<>();
        initialize();
    }

    private void initialize() {
        background = createBackground();
        text = new Text();
        imageView = new ImageView();
        getChildren().addAll(background, text, imageView);
        setAlignment(imageView, Pos.TOP_CENTER);
        setAlignment(text, Pos.BOTTOM_CENTER);
        addGrid(yPosition, xPosition, TERRITORY_GRID_SIZE);
        setCellColor(Color.LIGHTGREY);
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

    public Text getText() {
        return text;
    }

    public void setCellColor(Color color) {
        background.setFill(color);
    }

    public void setIslandCellColor() {
        Items item = getResident().getItem();
        if (item.is(Items.HERBIVORE)) {
            setCellColor(Color.OLIVEDRAB);
        } else if (item.is(Items.CARNIVORE)) {
            setCellColor(Color.BLACK);
        } else setCellColor(Color.OLIVEDRAB);
    }

    public Set<Organizm> getPopulation() {
        return population;
    }

    public Organizm getResident() {
            return population.iterator().next();
    }

    public Image getIcon() {
        return getResident().getIcon();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public Territory getTerritory() {
        return territory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return id == cell.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Cell secondCell) {
        // TODO: 19.06.2022 Исправить
        int result = 0;
        Items firstItem = getResident().getItem();
        Items secondItem = secondCell.getResident().getItem();

        if (firstItem.is(CARNIVORE) && secondItem.isNot(CARNIVORE)) {
            result = 1;
        } else if (firstItem.isNot(CARNIVORE) && secondItem.is(CARNIVORE)) {
            result = -1;
        } else if (firstItem.is(HERBIVORE) && secondItem.isNot(HERBIVORE)) {
            result = 1;
        } else if (firstItem.isNot(HERBIVORE) && secondItem.is(HERBIVORE)) {
            result = -1;
        } else if (firstItem.is(PLANT) && secondItem.isNot(PLANT)) {
            result = 1;
        } else if (firstItem.isNot(PLANT) && secondItem.is(PLANT)) {
            result = -1;
        }

        if (result == 0) {
            double firstTerritoryWeight = firstItem.getWeight() * getPopulation().size();
            double secondTerritoryWeight = secondItem.getWeight() * secondCell.getPopulation().size();
            result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
        }
        if (result == 0) {
            return Long.compare(id, secondCell.id);
        }
        return result;
    }
}
