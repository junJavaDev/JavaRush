package ru.javarush.ogarkov.cryptoanalizer.entity;

public class Result {
    private final String massage;
    private final ResultCode resultCode;

    public Result(String massage, ResultCode resultCode) {
        this.massage = massage;
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "massage='" + massage + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }

//    public void show() {
//        switch (resultCode) {
//            case FILE_NOT_FOUND -> System.out.printf("%s in this path: \n[%s]", resultCode.name(), fileIn);
//            case FALSE -> System.out.println("Something went wrong");
//            default -> System.out.printf("file [%s] \nSUCCESSFULLY %s \nto file [%s] \nUSING KEY [%d]", fileIn, resultCode.name(), fileOut, key);
//        }
//    }
}
