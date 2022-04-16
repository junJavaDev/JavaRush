package AbstractIntarface;

public interface InterfaceSecond {
    int fieldInterfaceFirst = 2;
    default void doSomething() {
        System.out.println("second");

    }

}
