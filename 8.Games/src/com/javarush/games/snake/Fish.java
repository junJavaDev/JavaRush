package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Fish extends GameObject {

    private static final String FISH_SIGN = "\uD83D\uDC1F";
    public boolean isAlive = true;

    public Fish(int x, int y) {
        super(x, y);
    }

    public void draw(Game game) {
        game.setCellValueEx(x, y, Color.NONE, FISH_SIGN, Color.BLUE, 75);
    }
}
