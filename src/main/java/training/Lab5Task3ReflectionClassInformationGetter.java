package training;

import training.entity.NotebookRecord;
import training.entity.NotebookRecordImpl;
import training.reflection.service.ReflectionLab5Task3;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
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
