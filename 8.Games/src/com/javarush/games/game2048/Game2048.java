package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }
    private void createGame() {
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(j, i, gameField[i][j]);
            }
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }
    private void moveLeft() {
        boolean isMoved = false;
        for (int i = 0; i < SIDE; i++) {
            isMoved = (compressRow(gameField[i]) ||
                    mergeRow(gameField[i]) ||
                    compressRow(gameField[i]));
            compressRow(gameField[i]);
            mergeRow(gameField[i]);
            compressRow(gameField[i]);
        }
    }
    private void moveRight() {}
    private void moveUp() {}
    private void moveDown() {}

    private boolean mergeRow(int[] row) {
        boolean isMerged = false;
        for (int i = 0; i < row.length-1; i++) {
            if (row[i] > 0 && row[i] == row[i+1]) {
                row[i] += row[i+1];
                row[i+1] = 0;
                i++;
                isMerged = true;
            }
        }
        return isMerged;
    }

    private boolean compressRow(int[] row) {
        boolean isCompressed = false;
        for (int i = 0; i < row.length-1; i++) {
            if (row [i] == 0) {
                for (int j = i+1; j < row.length; j++) {
                    if (row[j] > 0) {
                        row[i] = row[j];
                        row[j] = 0;
                        isCompressed = true;
                        break;
                    }
                }
            }
        }
        return isCompressed;
    }


    private void setCellColoredNumber (int x, int y, int value) {
        Color color = getColorByValue(value);
        String valueForShow = value > 0 ? "" + value : "";
        setCellValueEx(x, y, color, valueForShow);
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0 :
                return Color.GRAY;
            case 2 :
                return Color.PINK;
            case 4 :
                return Color.DARKBLUE;
            case 8 :
                return Color.DODGERBLUE;
            case 16 :
                return Color.BLUE;
            case 32 :
                return Color.DARKGREEN;
            case 64 :
                return Color.GREEN;
            case 128 :
                return Color.ORANGE;
            case 256 :
                return Color.DARKORANGE;
            case 512 :
                return Color.RED;
            case 1024 :
                return Color.VIOLET;
            case 2048 :
                return Color.DARKVIOLET;
            default:
                return Color.NONE;
        }

    }


    private void createNewNumber() {
        boolean isEmptyFound = false;
        int randomX;
        int randomY;
        while (!isEmptyFound) {
            randomX = getRandomNumber(SIDE);
            randomY = getRandomNumber(SIDE);
            if (gameField[randomX][randomY] == 0) {
                isEmptyFound = true;
                int randomNumber = getRandomNumber(10);
                if (randomNumber == 9) {
                    gameField[randomX][randomY] = 4;
                } else {
                    gameField[randomX][randomY] = 2;
                }
            }
        }
    }


}
