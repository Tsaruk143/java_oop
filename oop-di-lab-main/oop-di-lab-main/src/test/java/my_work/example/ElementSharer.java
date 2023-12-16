package my_work.example;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ElementSharer {

    private final SecondElem secondElem;

    @Inject
    public ElementSharer(SecondElem secondElem) {
        this.secondElem = secondElem;
    }

    public SecondElem shareElem() {
        return secondElem;
    }

}
