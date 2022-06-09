package ru.javarush.ogarkov.islandsimulation.item.abstracts;

public abstract class Animal extends BasicItem {

    public void Eat() {
        System.out.println("Животное ест растения и/или других животных (если в их локации есть подходящая еда)");
    }

    public void Move() {
        System.out.println("Животное передвигается (в соседние локации)");
    }

    public void Reproduce() {
        System.out.println("Животное размножается (при наличии пары в их локации)");
    }

    public void Die() {
        System.out.println("Животное умирает (от голода или съедено)");
    }




}
