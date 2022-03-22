package ru.javarush.ogarkov.cryptoanalizer.entity;

import java.util.Objects;

public class QuadChar {
    char firstSymbol;
    char secondSymbol;
    char thirdSymbol;
    char fourthSymbol;

    public QuadChar(char firstSymbol, char secondSymbol, char thirdSymbol, char fourthSymbol) {
        this.firstSymbol = firstSymbol;
        this.secondSymbol = secondSymbol;
        this.thirdSymbol = thirdSymbol;
        this.fourthSymbol = fourthSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadChar quadChar = (QuadChar) o;
        return firstSymbol == quadChar.firstSymbol && secondSymbol == quadChar.secondSymbol && thirdSymbol == quadChar.thirdSymbol && fourthSymbol == quadChar.fourthSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSymbol, secondSymbol, thirdSymbol, fourthSymbol);
    }

    @Override
    public String toString() {
        return "" + firstSymbol + secondSymbol + thirdSymbol + fourthSymbol;
    }

    public char[] getValue() {
        return new char[]{firstSymbol, secondSymbol, thirdSymbol, fourthSymbol};
    }

    public int getSingleMismatch(QuadChar anotherQuadChar) {
        int match = 0;
        int mismatchIndex = 0;
        for (int i = 0; i < 4; i++) {
            if (this.getValue()[i] == anotherQuadChar.getValue()[i]) {
                match++;
            }
            else mismatchIndex = i;
        }
        return match == 3 ? mismatchIndex : -1;
    }

    public char getFirstSymbol() {
        return firstSymbol;
    }

    public char getSecondSymbol() {
        return secondSymbol;
    }

    public char getThirdSymbol() {
        return thirdSymbol;
    }

    public char getFourthSymbol() {
        return fourthSymbol;
    }
}
