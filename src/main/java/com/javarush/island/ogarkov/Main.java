package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Setting;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Island islandModel;
    private Territory territoryModel;
    private Statistics statistics = new Statistics();
    private View view;
    private Controller controller;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        createSimulation();
        loadSimulationForm(stage);
    }

    public void createSimulation () {
        territoryModel = Territory.createModel();
        view = new View();
        statistics = new Statistics();
        controller = new Controller(this, territoryModel,  view, statistics);
        Island.setController(controller);
        islandModel = Island.createModel();
        controller.setIslandModel(islandModel);
    }


    private void loadSimulationForm (Stage stage) throws IOException {
        // Загрузка формы simulationForm.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/simulationForm.fxml"));
        fxmlLoader.setController(controller);
        stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")))));
        Scene scene = new Scene((Parent) fxmlLoader.load(), Setting.ISLAND_WIDTH_FORM, Setting.ISLAND_HEIGHT_FORM);
        stage.setTitle(Setting.SIMULATION_NAME);
        stage.setScene(scene);
        stage.show();
    }
}