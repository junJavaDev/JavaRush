package AbstractIntarface;

public class InterfaceChild implements  InterfaceFirst, InterfaceSecond, InterfaceThird{

    @Override
    public void doSomething() {
        InterfaceSecond.super.doSomething();
    }
}
