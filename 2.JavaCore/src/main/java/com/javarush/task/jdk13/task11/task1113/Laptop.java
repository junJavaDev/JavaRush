package com.javarush.task.jdk13.task11.task1113;

public class Laptop {

    private final String name;
    private final OperationSystem os;

    public Laptop(String laptopName, String osName, String osVersion) {
        name = laptopName;
        os = new OperationSystem(osName, osVersion);

    }

    public void updateOs(String version) {
        os.setVersion(version);
    }

    public void printInfo() {
        System.out.println(name);
        System.out.println(os.getName());
        System.out.println(os.getVersion());
    };

    public String getName() {
        return name;
    }
}
