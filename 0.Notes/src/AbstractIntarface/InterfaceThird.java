package AbstractIntarface;

public interface InterfaceThird {
    int fieldInterfaceFirst = 3;
    default void doSomething() {
        System.out.println("third");

    }
}
