package com.javarush.island.ogarkov;

import com.javarush.island.ogarkov.entity.Statistics;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.services.OrganismWorker;
import com.javarush.island.ogarkov.services.StatisticsWorker;
import com.javarush.island.ogarkov.settings.Items;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Controller extends View {

    public static Controller control;
    private final Statistics statistics;
    private Island islandModel;

    public void setIslandModel(Island islandModel) {
        this.islandModel = islandModel;
    }

    private final Territory territoryModel;
    private View view;
    private Main main;

    public Controller(Main main, Island islandModel, Territory territoryModel, View view, Statistics statistics) {
        this.main = main;
        this.territoryModel = territoryModel;
        this.islandModel = islandModel;
        this.view = view;
        this.statistics = statistics;
    }

    // TODO: 15.06.2022 Времянка 
    @FXML
    void initialize() {
        updateTerritoryArea(territoryModel);
        initIslandField(islandModel);
        System.out.println("Эта строка выведется при инициализации");
    }

    @FXML
    public void restart(ActionEvent actionEvent) {
        new OrganismWorker(islandModel).run();
    }

    public void updateIslandAreaView() {
        updateIslandArea(islandModel);
    }

    public void updateTerritoryAreaView() {
        updateTerritoryArea(territoryModel);
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