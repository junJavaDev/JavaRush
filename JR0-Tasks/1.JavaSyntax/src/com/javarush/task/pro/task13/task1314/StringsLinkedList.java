package com.javarush.task.pro.task13.task1314;

import java.util.LinkedList;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();
//    private int size = 0;

    public void add(String value) {
        if (first.next == null) {
            Node node = new Node();
            node.value = value;
            first.next = node;
//            size++;
        }
        if (last.prev == null) {
            last.prev = first.next;
            return;
        }

        Node node = new Node();
        node.value = value;

        Node lastNode = last.prev;
        lastNode.next = node;
        node.prev = lastNode;
        last.prev = node;
//        size++;
    }

    public String get(int index) {
//      if (index < 0 || index >= size) return null; //- Валик не пропускает
        if (index < 0) return null;
        Node node = first.next;
        for (int i = 0; node != null && i < index; i++) {
            node = node.next;
        }
        return node != null ? node.value : null;
    }
//    ПРАВИЛЬНОЕ РЕШЕНИЕ:
//    public String get(int index) {
//        int currentIndex = 0;
//        Node currentElement = first.next;
//        while ((currentElement) != null) {
//            if(currentIndex == index) {
//                return currentElement.value;
//            }
//            currentElement = currentElement.next;
//            currentIndex++;
//        }
//        return null;
//    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}
