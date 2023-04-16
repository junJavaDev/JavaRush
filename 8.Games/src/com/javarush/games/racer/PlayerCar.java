package com.javarush.games.racer;

public class PlayerCar extends GameObject{

    public int speed = 1;
    private Direction direction;

    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
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
            x = x-1;
        } else if (direction == Direction.RIGHT) {
            x = x+1;
        }
    }
}
