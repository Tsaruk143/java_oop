package my_work.example;

import javax.inject.Inject;

public class A {
    private B b;
    @Inject
    public A(B b) {
        this.b = b;
    }
}
