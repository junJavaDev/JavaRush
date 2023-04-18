package com.javarush.games.minesweeper;
import com.javarush.engine.cell.*;

public class MinesweeperGame extends Game{
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        boolean isMine;
        for (int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField[x].length; y++) {
                isMine = false;
                if (getRandomNumber(10) == 8) {
                    isMine = true;
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.GRAY);
            }
        }
    }

}


