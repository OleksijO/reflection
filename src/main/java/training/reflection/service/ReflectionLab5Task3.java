package training.reflection.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class ReflectionLab5Task3 {
    private static final String NULL_POINTER = "Method parameter can not be null!";

    public String getObjectClassName(Object obj) {
        if (obj != null) {
            return obj.getClass().getName();
        }
        throw new RuntimeException(NULL_POINTER);
    }

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

    public String getModifiers(Executable executable) {
        int modifiers = executable.getModifiers();
        return getModifiers(modifiers);
    }

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

    private String getAnnotations(Executable executable){
        StringBuilder stringBuilder = new StringBuilder();
        int counter =1;
        for(Annotation annotaion:executable.getAnnotations()){
            stringBuilder.append("@").append("SomeAnnotation").append(counter++).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getModifiers(Class clazz) {
        int modifiers = clazz.getModifiers();
        return getModifiers(modifiers);
    }

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
