package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.Controller;
import com.javarush.island.ogarkov.entity.Organizm;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Island;
import com.javarush.island.ogarkov.entity.animals.Animal;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.repository.itemfactory.landform.LandformFactory;
import com.javarush.island.ogarkov.repository.itemfactory.plant.PlantFactory;
import com.javarush.island.ogarkov.settings.Items;
import javafx.application.Platform;

import java.util.Iterator;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Setting.ISLAND_COLS;
import static com.javarush.island.ogarkov.settings.Setting.ISLAND_ROWS;

public class OrganizmWorker implements Runnable{

    private final Island island;
    private final Controller controller = Controller.control;
    public OrganizmWorker(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                Territory currentTerritory = island.getTerritories()[row][col];
                Cell[][] cells = currentTerritory.getCells();

                for (int cellRow = 0; cellRow < cells.length; cellRow++) {
                    for (int cellCol = 0; cellCol < cells.length; cellCol++) {

                        Cell cell = cells[cellRow][cellCol];
                        Set<Organizm> organizms = cell.getPopulation();
                        // Итератор с модификацией
                        // Хрень ещё та
                        Iterator<Organizm> iterator = organizms.iterator();
                        while (iterator.hasNext()) {
                            Organizm organizm = iterator.next();
                            if (organizm.getItem().is(Items.ANIMAL)) {
                                Animal animal = (Animal) organizm;
                                Territory destination = animal.move(cell);
//                                System.out.println("\nANIMAL ITEM = " + animal.getItem());
//
//                                Cell cellDestination = destination.getSortedCells().first();
//                                System.out.println("Sorted Cells after");
//                                for (Cell sortedCell : destination.getSortedCells()) {
//                                    System.out.print(sortedCell.getResident().getItem() + " ");
//                                }
                                if (currentTerritory != destination) {
//                                    System.out.println(organizms);
                                    iterator.remove();
//                                    System.out.println(organizms + " REMOVED");
                                }
//                                currentTerritory.getSortedCells().update(cell);

                            }
                            if (organizm.getItem().is(Items.LANDFORM)) {
                                cell.getPopulation().clear();
                                cell.getPopulation().add(new PlantFactory().createItem());
                            }
                        }
                        if (cell.getPopulation().isEmpty()) {
                            cell.getPopulation().add(new LandformFactory().createItem());
                        }
                    }
                }
                currentTerritory.addMouseClickedAction();
//                currentTerritory.updateTerritoryView();
            }
        }
        Platform.runLater(new IslandUpdateWorker(island, controller));

    }

    public void resetIslandColor() {
        for (int row = 0; row < ISLAND_ROWS; row++) {
            for (int col = 0; col < ISLAND_COLS; col++) {
                island.getTerritories()[row][col].getLeader().setIslandCellColor();
            }
        }
    }


}
