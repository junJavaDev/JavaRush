package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;

    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        check();
        Bullet fire = enemyFleet.fire(this);
        if (fire != null) enemyBullets.add(fire);
        drawScene();
    }


    private void createGame() {
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        setTurnTimer(40);
        createStars();
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        playerShip.draw(this);
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.draw(this);
        }

    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }

        for (Star star : stars) {
            star.draw(this);
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.move();
        }
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            stars.add(new Star(x, y));
        }
    }

    private void removeDeadBullets() {
        List<Bullet> desc = new ArrayList<>(enemyBullets);
        for (Bullet bullet : desc) {
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1) {
                enemyBullets.remove(bullet);
            }
        }
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        if (!playerShip.isAlive) stopGameWithDelay();
        removeDeadBullets();
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.GRAY, "YOU WIN!", Color.GREEN, 22);
        } else {
            showMessageDialog(Color.GRAY, "YOU LOSE!", Color.RED, 22);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if(isGameStopped) createGame();
                break;
            case LEFT:
                playerShip.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerShip.setDirection(Direction.RIGHT);
                break;
        }
    }
}
