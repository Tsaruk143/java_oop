package my_work.example;

import javax.inject.Inject;

public class B {
    private C c;
    @Inject
    public B(C c) {
        this.c = c;
    }
}
