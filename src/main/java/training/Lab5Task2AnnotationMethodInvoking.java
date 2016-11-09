package training;

import training.annotation.ReflectionInvocation;
import training.entity.NotebookRecord;
import training.entity.NotebookRecordImpl;
import training.reflection.service.ReflectionLab5Task3;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * This class represents a solved task 2 of Lab 5.
 *
 * Разработать аннотацию, отметить, ей метод(ы) в классе,
 * С помощью рефлексии обойти методы класса и вызвать отмеченные аннотацией методы с помощью invoke().
 *
 * @author oleksij.onysymchuk@gmail
 * @version 1.0 08 NOV 2016
 */
public class Lab5Task2AnnotationMethodInvoking {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        NotebookRecord rec = new NotebookRecordImpl(new Date(), "Full Name", "+38000000000");
        Class clazz = rec.getClass();
        Set<Method> methods = new LinkedHashSet<>();
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        methods.addAll(Arrays.asList(clazz.getMethods()));
        ReflectionLab5Task3 reflectionService = new ReflectionLab5Task3();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(ReflectionInvocation.class);
            if (annotation == null) {
                continue;
            }
            printMessage("Invoking: " +
                    reflectionService.getModifiers(method) +
                    method.getName() +
                    "(" + reflectionService.getParameters(method) + ");");
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            Object returned = method.invoke(rec);
            printMessage("Result: " + returned);
        }

    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
