package my_work.example;

import javax.inject.Inject;

public class ClassWithNestedInjection {

    private final ElementSharer sharer;

    @Inject
    public ClassWithNestedInjection(ElementSharer sharer) {
        this.sharer = sharer;
    }

    public ElementSharer getSharer() {
        return sharer;
    }

}
