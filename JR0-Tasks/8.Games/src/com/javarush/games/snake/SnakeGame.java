package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 40;
    private int score;
    private int scoreWeight = 5;
    private Snake snake;
    private Mouse mouse;
    private boolean isGameStopped;
    private int turnDelay;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();

    }

    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        score = 0;
        setScore(score);
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKGRAY, "");
            }
        }
        snake.draw(this);
        mouse.draw(this);

    }

    @Override
    public void onTurn(int step) {
        snake.move(mouse);
        if (!mouse.isAlive) {
            createNewApple();
            score += scoreWeight;
            scoreWeight++;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            mouse.isAlive = true;
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case LEFT: {
                snake.setDirection(Direction.LEFT);
                break;
            }
            case RIGHT: {
                snake.setDirection(Direction.RIGHT);
                break;
            }
            case UP: {
                snake.setDirection(Direction.UP);
                break;
            }
            case DOWN: {
                snake.setDirection(Direction.DOWN);
                break;
            }
            case SPACE: {
                if (isGameStopped) {
                    createGame();
                }
            }
        }
    }

    private void createNewApple() {
        do {
            mouse = new Mouse(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        } while (snake.checkCollision(mouse));
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        Snake.HEAD_SIGN = Snake.HEAD_SAD;
        showMessageDialog(Color.RED, "GAME OVER", Color.BLACK, 50);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        Snake.HEAD_SIGN = Snake.HEAD_SO_HAPPY;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.BLACK, 50);
    }




}
