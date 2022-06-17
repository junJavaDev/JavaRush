package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.services.StatisticsWorker;
import com.javarush.island.ogarkov.settings.Items;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Controller extends View {


    private final Statistics statistics;
    private Island islandModel;

    public void setIslandModel(Island islandModel) {
        this.islandModel = islandModel;
    }

    private final Territory territoryModel;
    private View view;
    private Main main;

    public Controller(Main main, Territory territoryModel, View view, Statistics statistics) {
        this.main = main;
        this.territoryModel = territoryModel;
        this.view = view;
        this.statistics = statistics;
    }

    // TODO: 15.06.2022 Времянка 
    @FXML
    void initialize() {
        initIslandField(islandModel);
        initLocationField(territoryModel);
        System.out.println("Эта строка выведется при инициализации");
    }

    @FXML
    public void restart(ActionEvent actionEvent) {
        initIslandField(islandModel);
        initLocationField(territoryModel);
    }

    public void updateIslandField() {
        islandModel.reload();
        initIslandField(islandModel);
    }


    @FXML
    public void showStatistic(ActionEvent actionEvent) {
        // TODO: 15.06.2022 переделать в поток пулы и т.п. 
        new StatisticsWorker(statistics).run();
        List<Text> items = new ArrayList<>();
        for (Items item : Items.values()) {
            Text text = new Text(item + " - " + statistics.getCreated().get(item) + "\n");
            items.add(text);
        }
        initStatistic(items);
    }
}