package com.javarush.island.ogarkov.repository;

import com.javarush.island.ogarkov.entity.Organizm;
import com.javarush.island.ogarkov.entity.landform.Landform;
import com.javarush.island.ogarkov.entity.plant.Plant;
import com.javarush.island.ogarkov.location.Cell;
import com.javarush.island.ogarkov.location.Territory;
import com.javarush.island.ogarkov.settings.Items;
import com.javarush.island.ogarkov.util.Randomizer;

import static com.javarush.island.ogarkov.settings.Items.*;

public class CellCreator {


    public Cell createRandomFilledCell(int row, int col, Territory territory) {
        Cell cell = new Cell(row, col, territory);
        fillByPlants(cell);
        fillByAnimals(cell);
        return cell;
    }

    public Cell createEmptyCell(int row, int col, Territory territory) {
        Cell cell = new Cell(row, col, territory);
        fillByLandform(cell);
        return cell;
    }


    public void fillByPlants(Cell cell) {
        int amount = 1 + Randomizer.getInt(30);
        Items randomPlantItem = PLANT.getRandom();
        for (int i = 0; i < amount; i++) {
            Plant plant = (Plant) randomPlantItem.getFactory().createItem();
            cell.getPopulation().add(plant);
        }
    }

    // TODO: 14.06.2022 Времянка
    public void fillByAnimals(Cell cell) {
        int isAnimal = Randomizer.getInt(100);
        if (isAnimal < 5) {
            Items randomCarnivoreItem = CARNIVORE.getRandom();
            int amount = 1 + Randomizer.getInt(5);
            cell.getPopulation().clear();
            for (int i = 0; i < amount; i++) {
                cell.getPopulation().add(randomCarnivoreItem.getFactory().createItem());
            }
        } else if (isAnimal < 15) {
            Items randomHerbivoreItem = HERBIVORE.getRandom();
            int amount = 1 + Randomizer.getInt(5);
            cell.getPopulation().clear();
            for (int i = 0; i < amount; i++) {
                cell.getPopulation().add(randomHerbivoreItem.getFactory().createItem());
            }
        }

    }

    public void fillByLandform(Cell cell) {
        Items randomLandformItem = LANDFORM.getRandom();
            Landform landform = (Landform) randomLandformItem.getFactory().createItem();
            cell.getPopulation().add(landform);
        }
    }
