package ru.javarush.ogarkov.island;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import ru.javarush.ogarkov.island.entity.Statistics;
import ru.javarush.ogarkov.island.location.Island;
import ru.javarush.ogarkov.island.location.Location;
import ru.javarush.ogarkov.island.services.StatisticsWorker;
import ru.javarush.ogarkov.island.settings.Items;

import java.util.ArrayList;
import java.util.List;


public class Controller extends View {


    private final Statistics statistics;
    private Island islandModel;

    public void setIslandModel(Island islandModel) {
        this.islandModel = islandModel;
    }

    private final Location locationModel;
    private View view;
    private Main main;

    public Controller(Main main, Location locationModel, View view, Statistics statistics) {
        this.main = main;
        this.locationModel = locationModel;
        this.view = view;
        this.statistics = statistics;
    }

    // TODO: 15.06.2022 Времянка 
    @FXML
    void initialize() {
//        initIslandField(islandModel);
//        initLocationField(locationModel);
        System.out.println("Эта строка выведется при инициализации");
    }

    @FXML
    public void restart(ActionEvent actionEvent) {
        initIslandField(islandModel);
        initLocationField(locationModel);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO: 16.06.2022 Перенести логику в сервисы, разобраться почему нет движухи
//                while (true){
//                       Sleeper.sleep(1000);
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//
//                                islandModel.reload();
//                                initIslandField(islandModel);
//                            }
//                    });
//                }
//
//            }
//        }).start();

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