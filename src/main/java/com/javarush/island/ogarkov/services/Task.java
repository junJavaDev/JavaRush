package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Organism;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class Task implements Callable<Organism> {
    private final Organism organism;
    private final Consumer<Organism> action;

    public Task(Organism organism, Consumer<Organism> action) {
        this.organism = organism;
        this.action = action;
    }

    public void doAction() {
        action.accept(organism);
    }


    public void run() {
        action.accept(organism);
    }

    @Override
    public Organism call() throws Exception {
        action.accept(organism);
        return null;
    }
}
