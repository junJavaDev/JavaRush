package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final Color ROADSIDE_COLOR = Color.GREEN;
    private static final Color ROAD_COLOR = Color.GRAY;
    private static final Color DIVIDING_STRIP_COLOR = Color.WHITE;
    private RoadMarking roadMarking;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        drawScene();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
    }

    private void drawField() {
        for (int w = 0; w < WIDTH; w++) {
            for (int h = 0; h < HEIGHT; h++) {
                if (w < ROADSIDE_WIDTH || w >= WIDTH - ROADSIDE_WIDTH) {
                    setCellColor(w, h, ROADSIDE_COLOR);
                } else if (w == CENTER_X) {
                    setCellColor(w, h, DIVIDING_STRIP_COLOR);
                } else setCellColor(w, h, ROAD_COLOR);
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }
}
