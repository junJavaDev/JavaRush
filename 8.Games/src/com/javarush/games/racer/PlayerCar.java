package com.javarush.games.racer;

import com.javarush.games.racer.road.RoadManager;

public class PlayerCar extends GameObject {

    public int speed = 1;
    private Direction direction;

    private static final int playerCarHeight = ShapeMatrix.PLAYER.length;

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        if (direction == Direction.LEFT) {
            x = x - 1;
        } else if (direction == Direction.RIGHT) {
            x = x + 1;
        }
        if (x < RoadManager.LEFT_BORDER) {
            x = RoadManager.LEFT_BORDER;
        } else if (x > RoadManager.RIGHT_BORDER - width) {
            x = RoadManager.RIGHT_BORDER - width;
        }
    }

    public void stop() {
        matrix = ShapeMatrix.PLAYER_DEAD;
    }
}
