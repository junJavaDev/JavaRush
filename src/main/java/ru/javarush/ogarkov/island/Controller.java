package ru.javarush.ogarkov.island;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.javarush.ogarkov.island.location.Island;
import ru.javarush.ogarkov.island.location.Location;
import ru.javarush.ogarkov.island.settings.Items;

import java.util.ArrayList;
import java.util.List;


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
        initIslandField(Island.createModel());
        initLocationField(Location.createModel());
        System.out.println("Эта строка выведется при инициализации");
    }

    @FXML
    public void restart(ActionEvent actionEvent) {
        initialize();
    }

    @FXML
    public void showStatistic(ActionEvent actionEvent) {
        List<Text> items = new ArrayList<>();
        for (Items item : Items.values()) {
            if (item.getChildren().isEmpty()) {
                Text text = new Text(item + " - " + item.getCreatedItemsCount() + "\n");
                items.add(text);
            }
        }
        initStatistic(items);
    }

}