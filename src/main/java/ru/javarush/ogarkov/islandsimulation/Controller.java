package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Random;


public class Controller extends View {

    private final Island islandModel;
    private Location locationModel;
    private View view;
    private Main main;

    public Controller(Main main, Island islandModel, Location locationModel, View view) {
        this.islandModel = islandModel;
        this.locationModel = locationModel;
        this.view = view;
        this.main = main;
    }

    @FXML
    void initialize() {
        locationModel.initialize();
        islandModel.initialize();
        initIslandField(islandModel);
        initLocationField(locationModel);
        Location.setModel(locationModel);
        Island.setModel(islandModel);
        System.out.println("Эта строка выведется при инициализации");
    }

    Random random = new Random();

    public void restart(ActionEvent actionEvent) {
        initialize();
    }


}