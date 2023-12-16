package my_work;

import org.fpm.di.Binder;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class CBinder implements Binder {

    private final Map<Class<?>, Class<?>> classesMap;
    private final Map<Class<?>, Object> instancesMap;

    public CBinder(Map<Class<?>, Class<?>> classesMap, Map<Class<?>, Object> instancesMap) {
        this.classesMap = classesMap;
        this.instancesMap = instancesMap;
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        if (clazz == null) {
            throw new BindException("Значення null. Байнд неможливий.");
        }
        bind(clazz, clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        if (classesMap.containsKey(clazz) || instancesMap.containsKey(clazz)) {
            throw new BindException("Повторний байндінг заборонено.");
        }

        int implMod = implementation.getModifiers();
        if (Modifier.isAbstract(implMod) || Modifier.isInterface(implMod)) {
            throw new BindException("Імплементація абстрактна.");
        }

        int injectAnnotationsCount = findAllInjectConstructors(implementation).size();
        if (injectAnnotationsCount > 1) {
            throw new BindException("Імплементація не може мати кілька впроваджень залежностей через конструктор.");
        }

        checkForCircularInjection(implementation);

        classesMap.put(clazz, implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        if (classesMap.containsKey(clazz) || instancesMap.containsKey(clazz)) {
            throw new BindException("Повторний байндінг заборонено.");
        }
        instancesMap.put(clazz, instance);
    }

    private List<Constructor<?>> findAllInjectConstructors(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors()).filter((constructor) ->
                constructor.getAnnotation(Inject.class) != null).toList();
    }

    private Optional<Constructor<?>> findAnyInjectConstructor(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors()).filter((constructor) ->
                constructor.getAnnotation(Inject.class) != null).findAny();
    }

    private void checkForCircularInjection(Class<?> clazz) {
        Queue<Class<?>> toBeCheckedQueue = new ArrayDeque<>();
        List<Class<?>> checked = new ArrayList<>();
        toBeCheckedQueue.add(clazz);
        while (!toBeCheckedQueue.isEmpty()) {
            Class<?> toBeChecked = toBeCheckedQueue.poll();
            Optional<Constructor<?>> constructorOpt =
                    findAnyInjectConstructor(toBeChecked);
            if (constructorOpt.isEmpty()) {
                continue;
            }
            Constructor<?> constructor = constructorOpt.get();
            Collections.addAll(toBeCheckedQueue, constructor.getParameterTypes());
            checked.add(toBeChecked);
            if (checked.stream().distinct().count() != checked.size()) {
                throw new InfLoopException("Круговий інджект класу (попали в нескінченний цикл)");
            }
        }
    }
}