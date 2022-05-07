package com.javarush.task.jdk13.task21.task2102;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/

public class Solution {
    private final String first;
    private final String last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Solution)) {
            return false;
        }
        Solution n = (Solution) o;
        if (!Objects.equals(first, n.first)) return false;

        return Objects.equals(last, n.last);
    }

    @Override
    public int hashCode () {
        int result = 31 * (first != null ? first.length() : 0);
        result += (last != null ? last.length() : 0);
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
