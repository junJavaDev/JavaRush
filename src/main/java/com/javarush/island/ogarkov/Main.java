package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.repository.IslandCreator;
import com.javarush.island.ogarkov.repository.TerritoryCreator;
import com.javarush.island.ogarkov.services.SimulationWorker;
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
        SimulationWorker simulationWorker = new SimulationWorker(islandModel, territoryModel, controller);
        simulationWorker.start();
    }

    public void createSimulation () {
        TerritoryCreator territoryCreator = new TerritoryCreator();
        IslandCreator islandCreator = new IslandCreator(territoryCreator);

        territoryModel = territoryCreator.createTerritoryModel(Setting.TERRITORY_ROWS, Setting.TERRITORY_COLS);
        Territory.modelView = territoryModel;
        islandModel = islandCreator.createIsland(Setting.ISLAND_ROWS, Setting.ISLAND_COLS);
        Island.model = islandModel;
        view = new View();
        statistics = new Statistics();
        controller = new Controller(this, islandModel, territoryModel,  view, statistics);
        Controller.control = controller;

        Island.setController(controller);
//        islandModel = Island.createModel();
        controller.setIslandModel(islandModel);
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