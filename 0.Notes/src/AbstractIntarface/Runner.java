package AbstractIntarface;

public class Runner {

    public static void main(String[] args) {
        InterfaceFirst interfaceChild = new InterfaceChild();
        AbstractClassChild abstractClass = new AbstractClassChild(3);
        interfaceChild.doSomething();
        System.out.println(abstractClass.abstractClassInt);
        abstractClass.doSomething();
    }
}
