package ru.javarush.ogarkov.islandsimulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 20;
    private static final int WIDTH_FORM = 1200;
    private static final int HEIGHT_FORM = 744;
    private static final int CELL_SIZE = 35;

    private IslandModel islandModel;
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
        islandModel = new IslandModel(WIDTH, HEIGHT, CELL_SIZE);
        view = new View();
        controller = new Controller(islandModel, view);
    }

    private void loadSimulationForm (Stage stage) throws IOException {
        // Загрузка формы simulationForm.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/simulationForm.fxml"));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), WIDTH_FORM, HEIGHT_FORM);
        stage.setTitle("Island Simulation by Ogarkov");
        stage.setScene(scene);
        stage.show();
    }
}