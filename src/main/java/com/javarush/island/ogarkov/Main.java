package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.repository.IslandCreator;
import com.javarush.island.ogarkov.repository.TerritoryCreator;
import com.javarush.island.ogarkov.services.SimulationWorker;
import com.javarush.island.ogarkov.settings.Setting;
import com.javarush.island.ogarkov.util.Sleeper;
import com.javarush.island.ogarkov.view.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private Island island;
    private Controller controller;
    private Statistics statistics;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        createSimulation();
        loadSimulationForm(stage);
        var simulationWorker = new SimulationWorker(island, controller, statistics);
        simulationWorker.start();
    }

    public void createSimulation () {
        var territoryCreator = new TerritoryCreator();
        var islandCreator = new IslandCreator(territoryCreator);
        island = islandCreator.createIsland(Setting.ISLAND_ROWS, Setting.ISLAND_COLS);
        Sleeper.sleep(1000);
        statistics = new Statistics();
        controller = new Controller(island, statistics);
    }


    private void loadSimulationForm (Stage stage) throws IOException {
        // Загрузка формы simulationForm.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/simulationForm.fxml"));
        fxmlLoader.setController(controller);
        stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")))));
        Scene scene = new Scene((Parent) fxmlLoader.load(), Setting.ISLAND_FORM_WIDTH, Setting.ISLAND_FORM_HEIGHT);
        stage.setTitle(Setting.SIMULATION_NAME);
        stage.setScene(scene);
        stage.show();
    }
}