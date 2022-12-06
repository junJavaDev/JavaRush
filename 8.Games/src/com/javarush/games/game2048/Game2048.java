package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score = 0;
    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            } else {
                return;
            }
        }

        if (!canUserMove()) {
            gameOver();
            return;
        }
        switch (key) {
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
        }
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }

    private boolean canUserMove() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (gameField[y][x] == 0) {
                    return true;
                }
                if (y < SIDE - 1 && gameField[y][x] == gameField[y + 1][x]) {
                    return true;
                }
                if (x < SIDE - 1 && gameField[y][x] == gameField[y][x + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createNewNumber() {
        if (getMaxTileValue() >= 2048) {
            win();
            return;
        }
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

    private int getMaxTileValue() {
        int maxTileValue = 0;
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                maxTileValue = Math.max(gameField[y][x], maxTileValue);
            }
        }
        return maxTileValue;
    }

    private void gameOver() {
        showMessageDialog(Color.RED, "ПРОИГРЫШ!\nНажмите пробел...", Color.BLACK, 24);
        isGameStopped = true;
    }

    private void win() {
        showMessageDialog(Color.GREEN, "ПОБЕДА!\nНажмите пробел...", Color.BLACK, 24);
        isGameStopped = true;
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color color = getColorByValue(value);
        String valueForShow = value > 0 ? "" + value : "";
        setCellValueEx(x, y, color, valueForShow);
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.GRAY;
            case 2:
                return Color.PINK;
            case 4:
                return Color.DARKBLUE;
            case 8:
                return Color.DODGERBLUE;
            case 16:
                return Color.BLUE;
            case 32:
                return Color.DARKGREEN;
            case 64:
                return Color.GREEN;
            case 128:
                return Color.ORANGE;
            case 256:
                return Color.DARKORANGE;
            case 512:
                return Color.RED;
            case 1024:
                return Color.VIOLET;
            case 2048:
                return Color.DARKVIOLET;
            default:
                return Color.NONE;
        }
    }

    private void moveLeft() {
        boolean isMoved = false;
        for (int[] row : gameField) {
            boolean wasCompressed = compressRow(row);
            boolean wasMerged = mergeRow(row);
            if (wasMerged) compressRow(row);
            if (wasCompressed || wasMerged) isMoved = true;
        }
        if (isMoved) createNewNumber();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private boolean compressRow(int[] row) {
        boolean isCompressed = false;
        for (int y = 0; y < row.length - 1; y++) {
            if (row[y] == 0) {
                for (int x = y + 1; x < row.length; x++) {
                    if (row[x] > 0) {
                        row[y] = row[x];
                        row[x] = 0;
                        isCompressed = true;
                        break;
                    }
                }
            }
        }
        return isCompressed;
    }

    private boolean mergeRow(int[] row) {
        boolean isMerged = false;
        for (int y = 0; y < row.length - 1; y++) {
            if (row[y] > 0 && row[y] == row[y + 1]) {
                row[y] += row[y + 1];
                score += row[y];
                setScore(score);
                row[y + 1] = 0;
                y++;
                isMerged = true;
            }
        }
        return isMerged;
    }

    private void rotateClockwise() {
        int[][] temp = new int[SIDE][SIDE];
        for (int y = 0; y < SIDE; y++) {
            System.arraycopy(gameField[y], 0, temp[y], 0, SIDE);
        }
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                gameField[y][x] = temp[SIDE - 1 - x][y];
            }
        }
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(j, i, gameField[i][j]);
            }
        }
    }
}