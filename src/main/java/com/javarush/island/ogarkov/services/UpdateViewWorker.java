package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.view.Controller;
import javafx.application.Platform;

public class UpdateViewWorker implements Runnable {
    private final Controller controller;

    public UpdateViewWorker(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.prepareForUpdateView();
        Platform.runLater(controller::updateView);
    }
}
