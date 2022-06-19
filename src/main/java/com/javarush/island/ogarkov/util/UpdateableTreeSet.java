package com.javarush.island.ogarkov.util;

import java.util.TreeSet;

public class UpdateableTreeSet<E> extends TreeSet<E> {


    public void update(E element) {
        if (remove(element)) {
            add(element);
        }
    }
}
