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
    private static final int PLAYER_BULLETS_MAX = 1;

    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;

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
        playerBullets = new ArrayList<>();
        setTurnTimer(40);
        createStars();
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        playerShip.draw(this);
        for (Bullet playerBullet : playerBullets) {
            playerBullet.draw(this);
        }
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
        playerShip.move();
        for (Bullet playerBullet : playerBullets) {
            playerBullet.move();
        }
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
        List<Bullet> playerDesc = new ArrayList<>(playerBullets);
        for (Bullet bullet : desc) {
            if (!bullet.isAlive || bullet.y >= HEIGHT - 1) {
                enemyBullets.remove(bullet);
            }
        }
        for (Bullet bullet : playerDesc) {
            if (!bullet.isAlive || bullet.y + bullet.height < 0) {
                playerBullets.remove(bullet);
            }
        }
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        if (playerShip.isAlive) stopGameWithDelay();
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
            stopGame(!playerShip.isAlive);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if(isGameStopped) createGame();
                else {
                    Bullet fire = playerShip.fire();
                    if (fire != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                        playerBullets.add(fire);
                    }
                };
                break;
            case LEFT:
                playerShip.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerShip.setDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case LEFT:
                if (playerShip.getDirection() == Direction.LEFT) {
                    playerShip.setDirection(Direction.UP);
                }
                break;
            case RIGHT:
                if (playerShip.getDirection() == Direction.RIGHT) {
                    playerShip.setDirection(Direction.UP);
                }
                break;
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return;
        }
        super.setCellValueEx(x, y, cellColor, value);
    }
}
