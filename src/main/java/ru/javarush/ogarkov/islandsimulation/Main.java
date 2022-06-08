package ru.javarush.ogarkov.islandsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private IslandModel islandModel;
    private LocationModel locationModel;
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



    private void createSimulation () {
        islandModel = new IslandModel(Setting.ISLAND_WIDTH, Setting.ISLAND_HEIGHT, Setting.CELL_SIZE);
        locationModel = new LocationModel(Setting.LOCATION_WIDTH, Setting.LOCATION_HEIGHT, Setting.CELL_SIZE);
        view = new View();
        controller = new Controller(islandModel, locationModel,  view);
    }

    private void loadSimulationForm (Stage stage) throws IOException {
        // Загрузка формы simulationForm.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/simulationForm.fxml"));
        fxmlLoader.setController(controller);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png"))));
        Scene scene = new Scene(fxmlLoader.load(), Setting.ISLAND_WIDTH_FORM, Setting.ISLAND_HEIGHT_FORM);
        stage.setTitle("Island Simulation by Ogarkov");
        stage.setScene(scene);
        stage.show();
    }
}