package AbstractIntarface;

public abstract class AbstractClass implements Cloneable {
    int abstractClassInt = 1;

    public AbstractClass(int abstractClassInt) {
        this.abstractClassInt = abstractClassInt;
    }

    protected void  doSomething() {
    }
}
