package com.javarush.task.jdk13.task27.task2705;

public class Apartment {
    private String location;
    private final RealEstate realEstate;

    public Apartment(RealEstate realEstate) {
        this.realEstate = realEstate;
        setLocation(String.valueOf(Math.random() * 10));
    }

    public String getLocation() {
        return location;
    }

    public  void setLocation(String location) {
        synchronized (this) {
            this.location = location;
        }
    }

    public void revalidate(boolean isEmpty) {
        synchronized (this) {
            if (isEmpty)
                realEstate.up(this);
        }
    }
}
