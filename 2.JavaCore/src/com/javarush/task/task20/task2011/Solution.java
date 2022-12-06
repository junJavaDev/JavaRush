package com.javarush.task.task20.task2011;

import java.io.*;

/* 
Externalizable для апартаментов
*/

public class Solution {

    public static class Apartment implements Externalizable {

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() {
            super();
        }

        public Apartment(String addr, int y) {
            address = addr;
            year = y;
        }

        /**
         * Prints out the fields used for testing!
         */
        public String toString() {
            return ("Address: " + address + "\n" + "Year: " + year);
        }

        @Override
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(address);
        objectOutput.writeInt(year);
        }

        @Override
        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.address = (String)objectInput.readObject();
        this.year = objectInput.readInt();
        }
    }

    public static void main(String[] args) {
        Apartment ap1 = new Apartment();
        ap1.year = 2021;
    }
}
