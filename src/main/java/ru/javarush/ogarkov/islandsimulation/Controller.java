package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.javarush.ogarkov.islandsimulation.item.abstracts.BasicItem;
import ru.javarush.ogarkov.islandsimulation.item.fauna.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.carnivore.*;
import ru.javarush.ogarkov.islandsimulation.item.flora.herbivore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Controller extends View {

    // контролер должен знать модель
    // в модели все вычисления и внутренние данные
//    private IslandModel islandModel = new IslandModel(100, 20, 35);
    private IslandModel islandModel;
    private LocationModel locationModel;
    private View view;

    public Controller(IslandModel islandModel, LocationModel locationModel, View view) {
        this.islandModel = islandModel;
        this.locationModel = locationModel;
        this.view = view;
    }

    int x = 0;
    int y = 3;

    @FXML
    void initialize() {
        fillIsland();
        islandModel.initialize();
        locationModel.initialize();
        System.out.println("Эта строка выведется при инициализации");
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        updateIslandField(islandModel);
        updateLocationField(locationModel);
        System.out.println("Обновлено");
    }

    // TODO: 08.06.2022 Времянка для отображения
    List<BasicItem> island = new ArrayList<>();
    private void fillIsland() {
        island.add(new Bush());
//        island.add(new Dandelion());
//        island.add(new Flower());
        island.add(new Grass());
//        island.add(new Soil());
        island.add(new Sprout());
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


    Tree tree1 = new Tree();
    Tree tree2 = new Tree();


    Random random = new Random();

    public void doSomething(ActionEvent actionEvent) {
        for (int x = 0; x < Setting.ISLAND_WIDTH; x++) {
            for (int y = 0; y < Setting.ISLAND_HEIGHT; y++) {
                int floraOrFauna = random.nextInt(10);
                int randomItem;
                if (floraOrFauna != 0) {
                    randomItem = random.nextInt(3);
                } else {
                    randomItem = 7 + random.nextInt(11);
                }
                BasicItem item = island.get(randomItem);
                islandModel.getLocations()[x][y].setCellImage(item.getIcon());
            }
        }
    }
}