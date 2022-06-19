package com.javarush.island.ogarkov.util;

import com.javarush.island.ogarkov.entity.Organism;
import com.javarush.island.ogarkov.location.Cell;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import static com.javarush.island.ogarkov.settings.Items.*;
import static com.javarush.island.ogarkov.settings.Items.PLANT;

public class CellComparator {
        private static final Comparator<Map.Entry<Cell, Set<Organism>>> CELL_COMPARATOR = (first, second) -> {
            int result = 0;
            var populationFirst = first.getValue();
            var populationSecond = second.getValue();
            // TODO: 20.06.2022 Нужна ли проверка на пустые ячейки и null
            var firstItem = populationFirst.iterator().next().getItem();
            var secondItem = populationSecond.iterator().next().getItem();

            if (firstItem.is(CARNIVORE) && secondItem.isNot(CARNIVORE)) {
                result = 1;
            } else if (firstItem.isNot(CARNIVORE) && secondItem.is(CARNIVORE)) {
                result = -1;
            } else if (firstItem.is(HERBIVORE) && secondItem.isNot(HERBIVORE)) {
                result = 1;
            } else if (firstItem.isNot(HERBIVORE) && secondItem.is(HERBIVORE)) {
                result = -1;
            } else if (firstItem.is(PLANT) && secondItem.isNot(PLANT)) {
                result = 1;
            } else if (firstItem.isNot(PLANT) && secondItem.is(PLANT)) {
                result = -1;
            }

            if (result == 0) {
                double firstTerritoryWeight = firstItem.getWeight() * populationFirst.size();
                double secondTerritoryWeight = secondItem.getWeight() * populationSecond.size();
                result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
            }
            if (result == 0) {
                var firstCell = first.getKey();
                var secondCell = second.getKey();
                return Long.compare(firstCell.getCellId(), secondCell.getCellId());
            }
            return result;
        };

        public static Comparator<Map.Entry<Cell, Set<Organism>>> get() {
            return CELL_COMPARATOR;
        }
    }
