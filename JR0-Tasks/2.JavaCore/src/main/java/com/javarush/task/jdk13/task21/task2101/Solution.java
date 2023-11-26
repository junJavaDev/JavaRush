package com.javarush.task.jdk13.task21.task2101;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
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
        if (this.getClass() != o.getClass()) return false;
            Solution solution = (Solution) o;

//      Первый вариант решения
//            if (this.first == null) {
//                if (solution.first != null) return false;
//                if (last == null) {
//                    return solution.last == null;
//                }
//                return last.equals(solution.last);
//            }
//
//            if (last == null) {
//                if (solution.last != null) return  false;
//                return this.first.equals(solution.first);
//            }
//
//            return this.first.equals(solution.first) && last.equals(solution.last);

//        Второй вариант решения
//        return (first == solution.first || first != null && first.equals(solution.first)) &&
//                (last == solution.last || last != null && last.equals(solution.last));

//        Третий вариант решения
            if (!Objects.equals(first, solution.first)) return false;
            return Objects.equals(last, solution.last);
        }

    @Override
    public int hashCode() {
        Objects.hashCode(first);
        return 31 * ((first != null) ? first.hashCode() : 0)
                  + (last != null ? last.hashCode() : 0);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
