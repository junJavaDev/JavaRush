package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Setting;
import javafx.scene.paint.Color;

import static com.javarush.island.ogarkov.settings.Setting.*;

public class TerritoryCreator {

    CellCreator cellCreator;

    public TerritoryCreator(CellCreator cellCreator) {
        this.cellCreator = cellCreator;
    }

    public Territory createTerritory(int rows, int cols) {
        Territory territory = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var cell = cellCreator.createRandomFilledCell(row, col, territory);
                territory.setCell(row, col, cell);
            }
        }
        territory.getLeader();
        territory.addMouseClickedAction();
        return territory;
    }

    public Territory createTerritoryModel(int rows, int cols) {
        Territory model = new Territory(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                var cell = cellCreator.createEmptyCell(row, col, model);
                model.setCell(row, col, cell);
            }
        }
        initModel(model);
        return model;
    }

    private void initModel(Territory model) {
        for (int x = 0; x < TERRITORY_ROWS; x++) {
            for (int y = 0; y < TERRITORY_COLS; y++) {
                Cell modelCell = model.getCells()[x][y];
                modelCell.setCellColor(Color.LIGHTGREY);
                modelCell.getCellBackground().setHeight(Setting.TERRITORY_CELL_HEIGHT);
                modelCell.addGrid(y, x, TERRITORY_GRID_SIZE);
            }
        }
    }

}
