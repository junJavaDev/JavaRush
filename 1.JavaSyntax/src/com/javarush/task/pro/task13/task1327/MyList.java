package com.javarush.task.pro.task13.task1327;

import java.util.*;

public class MyList<C> extends LinkedList {
    @Override
    public boolean addAll(Collection c) {
        ArrayList list = new ArrayList(c);
        Collections.sort(list);

        return super.addAll(list);
    }
}
