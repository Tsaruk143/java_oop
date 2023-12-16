package my_work.example;

import javax.inject.Inject;

public class ClassWithTwoConstructorInjection {

    private SecondElem secondElem;
    private ElementSharer elementSharer;

    @Inject
    public ClassWithTwoConstructorInjection(SecondElem secondElem) {
        this.secondElem = secondElem;
    }

    @Inject
    public ClassWithTwoConstructorInjection(ElementSharer elementSharer) {
        this.elementSharer = elementSharer;
    }

}
