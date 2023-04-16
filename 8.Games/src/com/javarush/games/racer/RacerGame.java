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
    private PlayerCar player;
    private static final int GAME_SPEED = 40;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveAll();
        drawScene();
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        player.move();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
            player.setDirection(Direction.LEFT);
            break;
            case RIGHT:
            player.setDirection(Direction.RIGHT);
            break;
        }
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        setTurnTimer(GAME_SPEED);
        drawScene();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
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
