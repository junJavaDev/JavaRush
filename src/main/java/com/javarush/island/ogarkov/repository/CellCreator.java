package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Setting;
import javafx.scene.paint.Color;

import static com.javarush.island.ogarkov.settings.Setting.TERRITORY_GRID_SIZE;

public class CellCreator {

    public Cell createCell(int row, int col, Territory territory) {
        return new Cell(row, col, territory);
    }

    public Cell createTerritoryModelCell(int row, int col, Territory territory) {
        Cell cell = new Cell(row, col, territory);
        cell.setCellColor(Color.LIGHTGREY);
        cell.getCellBackground().setHeight(Setting.TERRITORY_CELL_HEIGHT);
        cell.addGrid(col, row, TERRITORY_GRID_SIZE);
        return cell;
    }
}
