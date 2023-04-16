package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    // Создание элементов игры
    private void createGame() {
        createStars();
        drawScene();
    }
    // Отрисовка элементов игры
    private void drawScene() {
        drawField();
    }

    // Отрисовка космоса
    private void drawField() {
        for (int w = 0; w < WIDTH; w++) {
            for (int h = 0; h < HEIGHT; h++) {
                setCellValueEx(w, h, Color.BLACK, "");
            }
        }
        for (Star star : stars) {
            star.draw(this);
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        stars.add(new Star(3, 7));
        stars.add(new Star(22, 33));
        stars.add(new Star(17, 44));
        stars.add(new Star(44, 6));
        stars.add(new Star(33, 34));
        stars.add(new Star(32, 7));
        stars.add(new Star(57, 24));
        stars.add(new Star(14, 37));
    }
}
