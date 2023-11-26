package ru.javarush.island.ogarkov.location;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Island {
    private final List<Territory> territories;
    private final Territory[][] islandMap;

    public Island(int rows, int cols) {
        territories = new ArrayList<>();
        islandMap = new Territory[rows][cols];
    }
}
