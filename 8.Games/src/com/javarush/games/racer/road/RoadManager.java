package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();

    private static final int PLAYER_CAR_DISTANCE = 12;
    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    public void draw(Game game) {
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject item : items) {
            item.move(item.speed + boost, items);
        }
        deletePassedItems();
    }

    public boolean checkCrush(PlayerCar playerCar) {
        for (RoadObject item : items) {
            if (item.isCollision(playerCar)) {
                return true;
            }
        }
        return false;
    }

    private boolean isThornExists() {
        for (RoadObject item : items) {
            if (item instanceof Thorn) {
                return true;
            }
        }
        return false;
    }

    private void generateThorn(Game game) {
        int randomNumber = game.getRandomNumber(100);
        if (randomNumber < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void deletePassedItems() {
        List<RoadObject> destItems = new ArrayList<>(items);
        for (RoadObject destItem : destItems) {
            if (destItem.y >= RacerGame.HEIGHT) {
                items.remove(destItem);
            }
        }
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject roadObject = createRoadObject(type, x, y);
        if (roadObject != null && isRoadSpaceFree(roadObject)) {
            items.add(roadObject);
        }
    }

    private void generateMovingCar(Game game) {
        int randomNumber = game.getRandomNumber(100);
        if (randomNumber < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }
    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        } else if (type == RoadObjectType.DRUNK_CAR) {
            return new MovingCar(x, y);
        } else {
            return new Car(RoadObjectType.CAR, x, y);
        }
    }

    private boolean isMovingCarExists() {
        for (RoadObject item : items) {
            if (item instanceof MovingCar) {
                return true;
            }
        }
        return false;
    }

    private void generateRegularCar(Game game) {
        int randomNumber = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (randomNumber < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }
}
