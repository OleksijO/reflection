package training.reflection.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * This class represents a util class for getting some information from reflection API
 * in text format for further delivering to user view (console)
 *
 *
 * @version 1.0 08 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class ReflectionLab5Task3 {
    private static final String NULL_POINTER = "Method parameter can not be null!";

    /**
     * @param obj the value of studying object
     * @return The full qualified class name
     */
    public String getObjectClassName(Object obj) {
        if (obj != null) {
            return obj.getClass().getName();
        }
        throw new RuntimeException(NULL_POINTER);
    }

    /**
     * @param obj the value of studying object
     * @return List of first line of a constructor with param types, modifiers and annotation presence markers above
     */
    public List<String> getConstructors(Object obj) {
        if (obj == null) {
            throw new RuntimeException(NULL_POINTER);
        }
        List<String> classConstructors = new ArrayList<>();
        Class clazz = obj.getClass();
        Set<Constructor> constructors = new LinkedHashSet<>();
        constructors.addAll(Arrays.asList(clazz.getDeclaredConstructors()));
        constructors.addAll(Arrays.asList(clazz.getConstructors()));
        for (Constructor constructor : constructors) {
            StringBuilder constructorSignatureBuilder = new StringBuilder();
            constructorSignatureBuilder.append(getAnnotations(constructor));
            constructorSignatureBuilder.append(getModifiers(constructor));
            constructorSignatureBuilder.append(constructor.getName()).append("(");
            constructorSignatureBuilder.append(getParameters(constructor));
            constructorSignatureBuilder.append(");");
            classConstructors.add(constructorSignatureBuilder.toString());
        }
        return classConstructors;
    }

    /**
     * @param executable the value of studying object - constructor or method
     * @return The string of modifiers
     */
    public String getModifiers(Executable executable) {
        int modifiers = executable.getModifiers();
        return getModifiers(modifiers);
    }

    /**
     * @param executable the value of studying object - constructor or method
     * @return The string of parameter types simple names
     */
    public String getParameters(Executable executable) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < executable.getParameterTypes().length; i++) {
            stringBuilder.append(executable.getParameterTypes()[i].getSimpleName());
            if (i < executable.getParameterTypes().length - 1){
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @param executable the value of studying object - constructor or method
     * @return The string with line separator of annotation presence markers
     */
    private String getAnnotations(Executable executable){
        StringBuilder stringBuilder = new StringBuilder();
        int counter =1;
        for(Annotation annotaion:executable.getAnnotations()){
            stringBuilder.append("@").append("SomeAnnotation").append(counter++).append("\n");
        }
        return stringBuilder.toString();
    }


    /**
     * @param clazz the value of studying object - class
     * @return The string of modifiers
     */
    public String getModifiers(Class clazz) {
        int modifiers = clazz.getModifiers();
        return getModifiers(modifiers);
    }

    /**
     * @param modifiers the value of result of getModifiers() method
     * @return The string of modifiers
     */
    private String getModifiers(int modifiers) {
        StringBuilder result = new StringBuilder();
        if (Modifier.isAbstract(modifiers)) {
            result.append("abstract ");
        }
        if (Modifier.isPrivate(modifiers)) {
            result.append("private ");
        }
        if (Modifier.isProtected(modifiers)) {
            result.append("protected ");
        }
        if (Modifier.isPublic(modifiers)) {
            result.append("public ");
        }
        if (Modifier.isFinal(modifiers)) {
            result.append("final ");
        }
        if (Modifier.isStatic(modifiers)) {
            result.append("static ");
        }
        if (Modifier.isFinal(modifiers)) {
            result.append("final ");
        }
        if (Modifier.isSynchronized(modifiers)) {
            result.append("synchronized ");
        }
        return result.toString();
    }


}
