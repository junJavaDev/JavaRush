package com.javarush.island.ogarkov.services;

import com.javarush.island.ogarkov.entity.Organism;

import java.util.function.Consumer;

public class Task {
    private final Organism organism;
    private final Consumer<Organism> action;

    public Task(Organism organism, Consumer<Organism> action) {
        this.organism = organism;
        this.action = action;
    }

    public void doAction() {
        action.accept(organism);
    }



}
