package ru.javarush.island.ogarkov.location;

import lombok.Getter;
import lombok.Setter;
import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.landform.Landform;
import ru.javarush.island.ogarkov.settings.Items;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static ru.javarush.island.ogarkov.settings.Items.ANIMAL;

@Getter
public class Cell implements Comparable<Cell> {
    private final Lock lock = new ReentrantLock(true);
    private final Territory territory;
    @Setter
    private Set<Organism> population;
    @Setter
    private Items residentItem;
    @Setter
    private Landform landform;

    public Cell(Territory territory) {
        this.territory = territory;
    }

    @Override
    public int compareTo(Cell secondCell) {
        int result = 0;
        Items firstItem = this.getResidentItem();
        Items secondItem = secondCell.getResidentItem();
        if (firstItem.is(ANIMAL) && secondItem.isNot(ANIMAL)) {
            result = 1;
        } else if (firstItem.isNot(ANIMAL) && secondItem.is(ANIMAL)) {
            result = -1;
        }

        if (result == 0) {
            Set<Organism> firstPopulation = this.getPopulation();
            Set<Organism> secondPopulation = secondCell.getPopulation();

            double firstTerritoryWeight = firstItem.getMaxWeight() * firstPopulation.size();
            double secondTerritoryWeight = secondItem.getMaxWeight() * secondPopulation.size();
            result = Double.compare(firstTerritoryWeight, secondTerritoryWeight);
        }
        return result;
    }


}
