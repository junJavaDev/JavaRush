package AbstractIntarface;

public interface InterfaceFirst {
   int fieldInterfaceFirst = 1;
   default void doSomething() {
      System.out.println("first");
   }

}
