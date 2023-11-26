package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();
    public static final String HEAD_NORMALLY = "\uD83D\uDE3C";
    public static final String HEAD_HAPPY = "\uD83D\uDE38";
    public static final String HEAD_SO_HAPPY = "\uD83D\uDE3A";
    public static final String HEAD_SAD = "\uD83D\uDE3F";

    public static String HEAD_SIGN = HEAD_NORMALLY;
    private static final String BODY_SIGN = "\u25A0";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw (Game game) {
        if (isAlive) {
            game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.BLACK, 75);
            for (int i = 1; i < snakeParts.size(); i++) {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.BLACK, 75);
            }
        }
        else {
            game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, Color.NONE, HEAD_SIGN, Color.RED, 75);
            for (int i = 1; i < snakeParts.size(); i++) {
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, Color.RED, 75);
            }
        }
    }

    public void setDirection(Direction newDirection) {

        if ((direction == Direction.UP || direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y ||
                (direction == Direction.LEFT || direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if (newDirection == Direction.UP && direction != Direction.DOWN  ||
                newDirection == Direction.DOWN && direction != Direction.UP ||
                newDirection == Direction.LEFT && direction != Direction.RIGHT ||
                newDirection == Direction.RIGHT && direction != Direction.LEFT) {
            direction = newDirection;
        }

    }

    public void move(Mouse mouse) {
        GameObject newHead = createNewHead();
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }
        if (newHead.x < 0) {
            newHead.x = SnakeGame.WIDTH - 1;
        }
        else if (newHead.x >= SnakeGame.WIDTH) {
            newHead.x = 0;
        }
        else if (newHead.y < 0) {
            newHead.y = SnakeGame.HEIGHT - 1;
        }
        else if (newHead.y >= SnakeGame.HEIGHT) {
            newHead.y = 0;
        }
            snakeParts.add(0, newHead);
            if (newHead.x == mouse.x && newHead.y == mouse.y) {
                mouse.isAlive = false;
                HEAD_SIGN = HEAD_SO_HAPPY;
            }
            else {
                HEAD_SIGN = HEAD_NORMALLY;
                removeTail();
            }
    }

    public GameObject createNewHead() {
        int newHeadX = snakeParts.get(0).x;
        int newHeadY = snakeParts.get(0).y;
        if (direction == Direction.LEFT) {
            newHeadX--;
        }
        else if (direction == Direction.RIGHT) {
            newHeadX++;
        }
        else if (direction == Direction.DOWN) {
            newHeadY++;
        }
        else newHeadY--;
        return new GameObject(newHeadX, newHeadY);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject snakePart : snakeParts) {
            if (snakePart.x == gameObject.x && snakePart.y == gameObject.y)
                return true;
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }
}
