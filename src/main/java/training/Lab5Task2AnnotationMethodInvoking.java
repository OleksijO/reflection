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
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class Lab5Task2AnnotationMethodInvoking {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        NotebookRecord rec = new NotebookRecordImpl(new Date(), "Full Name", "+38000000000");
        List<String> classMethods = new ArrayList<>();
        Class clazz = rec.getClass();
        Set<Method> methods = new LinkedHashSet<>();
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        methods.addAll(Arrays.asList(clazz.getMethods()));
        /*
        Class superClass;
        while((superClass = clazz.getSuperclass())!=null){
            methods.addAll(Arrays.asList(superClass.getDeclaredMethods()));
        }
        */
        ReflectionLab5Task3 reflectionService = new ReflectionLab5Task3();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(ReflectionInvocation.class);
            if (annotation==null){
                continue;
            }
            System.out.print("Invoking: ");
            System.out.print(reflectionService.getModifiers(method));
            System.out.print(method.getName());
            System.out.println("("+reflectionService.getParameters(method)+");");
            if (!method.isAccessible()){
                method.setAccessible(true);
            }
            Object returned = method.invoke(rec);
            System.out.println("Result: "+returned);
        }

    }


    public static void printMessage(String message) {
        System.out.println(message);
    }
}
