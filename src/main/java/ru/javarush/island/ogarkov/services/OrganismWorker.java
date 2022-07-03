package ru.javarush.island.ogarkov.services;

import ru.javarush.island.ogarkov.entity.Organism;
import ru.javarush.island.ogarkov.entity.animals.Animal;
import ru.javarush.island.ogarkov.location.Cell;
import ru.javarush.island.ogarkov.location.Territory;
import ru.javarush.island.ogarkov.settings.Items;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrganismWorker implements Callable<Boolean> {

    private final Items item;
    private final List<Territory> territories;
    private final Queue<Task> tasks;

    public OrganismWorker(Items item, List<Territory> territories) {
        this.item = item;
        this.territories = territories;
        tasks = new ConcurrentLinkedQueue<>();
    }

    @Override
    public Boolean call() {
        for (Territory territory : territories) {
            for (Cell cell : territory.getCells()) {
                if (item.is(cell.getResidentItem())) {
                        processCell(cell);
                }
            }
        }
        return true;
    }

    protected void processCell(Cell cell) {
        cell.getLock().lock();
        Set<Organism> population = cell.getPopulation();
        try {
            for (Organism organism : population) {
                Task task = new Task(organism, organismToAction -> {
                    Items organismItem = organismToAction.getItem();
                    if (item.is(organismItem)) {
                        organismToAction.reproduce(cell);
                        if (item.is(Items.ANIMAL)) {
                            Animal animal = (Animal) organismToAction;
                            animal.eat(cell);
                            animal.move(cell);
                        }
                    }
                });
                tasks.add(task);
            }
        } finally {
            cell.getLock().unlock();
        }
        tasks.forEach(Task::doAction);
        tasks.clear();
    }
}


