package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.item.fauna.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.carnivore.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.herbivore.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Участок локации, ячейка
public class Territory extends StackPane{

    private final Rectangle background;
    private Text text;
    private ImageView imageView;
    private int xPosition;
    private int yPosition;
    public BasicItem[] population;
    private static Random random = new Random();

    public Territory(Rectangle rectangle, Text text, ImageView imageView) {
        super(rectangle, text, imageView);
        this.background = rectangle;
        this.text = text;
        this.imageView = imageView;
        fillPopulation();
    }
    // TODO: 09.06.2022 Псевдофабрика

    static List<BasicItem> island = new ArrayList<>();
        static {
            island.add(new Bush());
            island.add(new Grass());
//        island.add(new Soil());
            island.add(new Sprout());
            island.add(new Dandelion());
            island.add(new Flower());
//        island.add(new Sunflower());
            island.add(new Tree());
            island.add(new Bear());
            island.add(new Boa());
            island.add(new Eagle());
            island.add(new Fox());
            island.add(new Wolf());
            island.add(new Boar());
            island.add(new Buffalo());
            island.add(new Caterpillar());
            island.add(new Deer());
            island.add(new Duck());
            island.add(new Goat());
            island.add(new Horse());
            island.add(new Mouse());
            island.add(new Rabbit());
            island.add(new Sheep());
        }
    public BasicItem createItem() {
                int floraOrFauna = random.nextInt(10);
                int flower = random.nextInt(20);
                int randomItem;
                if (floraOrFauna != 0) {
                    if (flower == 0) {
                        randomItem = 3;
                    } else if (flower == 1) {
                        randomItem = 4;
                    } else
                        randomItem = random.nextInt(3);
                } else randomItem = 7 + random.nextInt(13);
        return island.get(randomItem);
    }


    public void fillPopulation() {
        int amount = 1 + random.nextInt(20);
        BasicItem item = createItem();
        population = new BasicItem[amount];
        Arrays.fill(population, item);
    }

    public void setPosition(int x, int y, int gridSize) {
        this.xPosition = x;
        this.yPosition = y;
        setLayoutX((x * (background.getWidth() + gridSize)));
        setLayoutY((y * (background.getHeight() + gridSize)));
    }

    public void setCellText(String text) {
        this.text.setText(text);
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

}
