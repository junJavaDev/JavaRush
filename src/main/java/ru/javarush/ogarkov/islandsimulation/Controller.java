package ru.javarush.ogarkov.islandsimulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ru.javarush.ogarkov.islandsimulation.location.Island;
import ru.javarush.ogarkov.islandsimulation.location.Location;


public class Controller extends View {

    private final Island islandModel;
    private final Location locationModel;
    private View view;
    private Main main;

    public Controller(Main main, Island islandModel, Location locationModel, View view) {
        this.main = main;
        this.islandModel = islandModel;
        this.locationModel = locationModel;
        this.view = view;
    }

    @FXML
    void initialize() {
//        locationModel.initialize();
//        islandModel.initialize();
        initIslandField(islandModel);
        initLocationField(locationModel);
        System.out.println("Эта строка выведется при инициализации");
    }

    public void restart(ActionEvent actionEvent) {
        initialize();
    }

}