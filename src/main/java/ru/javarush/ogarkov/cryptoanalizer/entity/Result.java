package ru.javarush.ogarkov.cryptoanalizer.entity;

import ru.javarush.ogarkov.cryptoanalizer.constants.Results;

public class Result {
    private final Results entity;
    private String fileIn = null;
    private String fileOut = null;
    private int key;

    public Result(Results entity, String[] parameters) {
        this.entity = entity;
        fileIn = parameters[0];
        fileOut = parameters[1];
        key = Integer.parseInt(parameters[2]);
    }

    public Result(Results entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return entity.name();
    }

    public void show() {
        switch (entity) {
            case FILE_NOT_FOUND -> System.out.printf("%s in this path: \n[%s]", entity.name(), fileIn);
            default -> System.out.printf("file [%s] \nSUCCESSFULLY %s \nto file [%s] \nUSING KEY [%d]", fileIn, entity.name(), fileOut, key);
        }
    }
}
