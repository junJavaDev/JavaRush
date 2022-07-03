package ru.javarush.island.ogarkov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.javarush.island.ogarkov.entity.Statistics;
import ru.javarush.island.ogarkov.location.Island;
import ru.javarush.island.ogarkov.repository.IslandCreator;
import ru.javarush.island.ogarkov.repository.TerritoryCreator;
import ru.javarush.island.ogarkov.services.SimulationWorker;
import ru.javarush.island.ogarkov.settings.ItemsLoader;
import ru.javarush.island.ogarkov.settings.Setting;
import ru.javarush.island.ogarkov.view.Controller;

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
        controller.setSimulationWorker(simulationWorker);
        simulationWorker.start();
    }

    public void createSimulation () {
        ItemsLoader.loadItems();
        var territoryCreator = new TerritoryCreator();
        var islandCreator = new IslandCreator(territoryCreator);
        island = islandCreator.createIsland(Setting.ISLAND_ROWS, Setting.ISLAND_COLS);
        statistics = new Statistics();
        controller = new Controller(island, statistics);
    }

    private void loadSimulationForm (Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class
                        .getResource("/ogarkov/simulationForm.fxml")
        );
        fxmlLoader.setController(controller);
        stage.getIcons().add(
                new Image(
                        (Objects.requireNonNull(getClass()
                                .getResourceAsStream("/ogarkov/icon.png")))
                ));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Setting.SIMULATION_NAME);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(controller.getCloseEventHandler());
    }
}