package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, y * STEP + 12));
            }
        }
    }

    public void draw(Game game) {
        for (EnemyShip ship : ships) {
            ship.draw(game);
        }
    }

    public void move() {
        if (ships.isEmpty()) {
            return;
        }
        double speed = getSpeed();
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, speed);
            }
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, speed);
            }
        } else {
            for (EnemyShip ship : ships) {
                ship.move(direction, speed);
            }
        }
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty()) {
            return null;
        }
        int randomNumber = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
        if (randomNumber > 0) {
            return null;
        }
        int randomShip = game.getRandomNumber(ships.size());
        return ships.get(randomShip).fire();
    }

    public void verifyHit(List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            for (EnemyShip ship : ships) {
                if (ship.isAlive && bullet.isAlive && ship.isCollision(bullet)) {
                    ship.kill();
                    bullet.kill();
                }
            }
        }
    }

    public void deleteHiddenShips() {
        for (EnemyShip ship : new ArrayList<>(ships)) {
            if (!ship.isVisible()) {
                ships.remove(ship);
            }
        }
    }

    private double getLeftBorder() {
        return ships.stream().mapToDouble(s -> s.x).min().getAsDouble();
    }

    private double getRightBorder() {
        return ships.stream().mapToDouble(s -> s.x + s.width).max().getAsDouble();
    }

    private double getSpeed() {
        return Math.min(2.0, 3.0 / ships.size());
    }
}
