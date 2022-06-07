package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.nio.charset.Charset;

public class Controller extends View {

    // контролер должен знать модель
    // в модели все вычисления и внутренние данные
//    private IslandModel islandModel = new IslandModel(100, 20, 35);
    private IslandModel islandModel;
    private View view;

    public Controller(IslandModel islandModel, View view) {
        this.islandModel = islandModel;
        this.view = view;
    }

    public Controller() {
    }

    int x = 0;
    int y = 19;

    @FXML
    void initialize() {
        islandModel.initialize();
        System.out.println("Эта строка выведется при инициализации");
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) {
        updateIslandField(islandModel);
        System.out.println("Обновлено");
    }

    public void doSomething(ActionEvent actionEvent) {
        islandModel.getLocations()[x][x].setCellImage("/island/soil.png");
        islandModel.getLocations()[x][y].setCellImage("/plants/dandelion.png");
        islandModel.getLocations()[y][x].setCellImage("/herbivores/buffalo.png");
        islandModel.getLocations()[y][y].setCellImage("/predators/boa.png");
        x++;
        y--;
    }
}