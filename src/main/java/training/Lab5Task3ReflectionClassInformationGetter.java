package training;

import training.entity.NotebookRecord;
import training.entity.NotebookRecordImpl;
import training.reflection.service.ReflectionLab5Task3;

import java.util.Date;

/**
 * This class represents a solved task 3 of Lab 5.
 *
 * С помощью рефлексии вывести имя класса, а также согласно варианта:
 * Задание 1. Список конструкторов с их параметрами (+модификаторы + маркеры аннотаций)
 * Задание 2. Модификаторы класса
 *
 * @author oleksij.onysymchuk@gmail
 * @version 1.0 08 NOV 2016
 */
public class Lab5Task3ReflectionClassInformationGetter {
    public static void main(String[] args) {
        NotebookRecord rec = new NotebookRecordImpl(new Date(), "Full Name", "+38000000000");
        ReflectionLab5Task3 reflectionService = new ReflectionLab5Task3();
        printMessage("Class of instance: ");
        printMessage(reflectionService.getObjectClassName(rec));
        printMessage("");
        printMessage("Constructors of instance: ");
        reflectionService.getConstructors(rec).forEach(Lab5Task3ReflectionClassInformationGetter::printMessage);
        printMessage("");
        printMessage("Instance class modifiers: ");
        printMessage(reflectionService.getModifiers(rec.getClass()));
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
