package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField = 0;
    private int countFlags = 0;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();

    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countFlags = countMinesOnField;
        isGameStopped = false;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField[x].length; y++) {
                if (!gameField[y][x].isMine) {
                    gameField[y][x].countMineNeighbors = (int)getNeighbors(gameField[y][x]).stream()
                            .filter(i -> i.isMine)
                            .count();
                }
            }
        }
    }

    private void openTile(int x, int y) {
        GameObject gameObject = gameField[y][x];
        if (isGameStopped || gameObject.isOpen || gameObject.isFlag) return;
        gameObject.isOpen = true;
        countClosedTiles --;
        setCellColor(x, y, Color.GREEN);
        if (gameObject.isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        }

        else if (gameObject.countMineNeighbors == 0) {
            getNeighbors(gameObject).stream()
                    .filter(i -> !i.isOpen)
                    .forEach(gameObject1 -> openTile(gameObject1.x, gameObject1.y));
            setCellValue(x, y, "");
            score += 5;
            setScore(score);
            if (countClosedTiles == countMinesOnField) win();
        }
        else {
            setCellNumber(x, y, gameObject.countMineNeighbors);
            score += 5;
            setScore(score);
            if (countClosedTiles == countMinesOnField) win();
        };

    }

    private void markTile(int x, int y) {
        if (isGameStopped) return;
        GameObject gameObject = gameField[y][x];
        if (!gameObject.isOpen && countFlags > 0 && !gameObject.isFlag) {
            gameObject.isFlag = true;
            countFlags--;
            setCellValue(x, y, FLAG);
            setCellColor(x, y, Color.YELLOW);
        }
        else if (!gameObject.isOpen && gameObject.isFlag) {
            gameObject.isFlag = false;
            countFlags++;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.ORANGE);
        }


    }

    private void gameOver() {
        showMessageDialog(Color.RED,"Ты взорвался", Color.BLACK,22);
        isGameStopped = true;
    }

    private void win() {
        showMessageDialog(Color.GREEN,"Поздравляю, сегодня тебе повезло!", Color.BLACK,22);
        isGameStopped = true;
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }
}