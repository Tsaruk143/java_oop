package my_work.example;

import my_work.BasicEnvironment;
import my_work.BindException;
import my_work.InfLoopException;
import my_work.UCException;
import org.fpm.di.Container;
import org.fpm.di.Environment;
import static org.junit.Assert.assertSame;
import org.junit.Test;

public class MyTest {

    private final Environment environment = new BasicEnvironment();

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRebindWithClass() {
        environment.configure((binder) -> {
            binder.bind(AbstractElement.class, SecondElem.class);
            binder.bind(AbstractElement.class, FirstElement.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRebindWithInstance() {
        environment.configure((binder) -> {
            binder.bind(AbstractElement.class, SecondElem.class);
            binder.bind(AbstractElement.class, new FirstElement());
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterAbstractClass() {
        environment.configure((binder) -> {
            binder.bind(AbstractElement.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterInterface() {
        environment.configure((binder) -> {
            binder.bind(OperableItem.class);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterNull() {
        environment.configure((binder) -> {
            binder.bind(null);
        });
    }

    @Test(expected = BindException.class)
    public void shouldThrowBindExceptionWhenTryingToRegisterClassWithMoreThanOneInjectionConstructor() {
        environment.configure((binder) -> {
            binder.bind(ClassWithTwoConstructorInjection.class);
        });
    }

    @Test(expected = UCException.class)
    public void shouldThrowUnregisteredComponentExceptionWhenTryingToGetUnregisteredComponent() {
        Container container = environment.configure((binder) -> {});
        container.getComponent(SecondElem.class);
    }

    @Test(expected = UCException.class)
    public void shouldThrowUnregisteredComponentExceptionWhenTryingToGetComponentWhichDependenciesNotRegistered() {
        Container container = environment.configure((binder) -> {
            binder.bind(ElementSharer.class);
        });
        container.getComponent(ElementSharer.class);
    }

    @Test(expected = InfLoopException.class)
    public void shouldThrowCircularInjectExceptionWhenTryingToRegisterComponentWithCircularInjectDependency() {
        environment.configure((binder) -> {
            binder.bind(A.class);
            binder.bind(B.class);
            binder.bind(C.class);
        });
    }

    @Test
    public void shouldResolveSingletonWithInjection() {
        Container container = environment.configure((binder) -> {
            binder.bind(SecondElem.class);
            binder.bind(ElementSharer.class);
        });
        /* @Singleton AppleSharer */
        ElementSharer sharer1 = container.getComponent(ElementSharer.class);
        ElementSharer sharer2 = container.getComponent(ElementSharer.class);
        assertSame(sharer1, sharer2);
        assertSame(sharer1.shareElem(), sharer2.shareElem());
    }

    @Test
    public void shouldResolveNestedInjectDependency() {
        Container container = environment.configure((binder) -> {
            binder.bind(ClassWithNestedInjection.class);
            binder.bind(SecondElem.class);
            binder.bind(ElementSharer.class);
        });
        ClassWithNestedInjection c = container.getComponent(ClassWithNestedInjection.class);
        assertSame(c.getSharer(), container.getComponent(ElementSharer.class));
    }

}
