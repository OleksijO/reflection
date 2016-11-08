package training;

import training.entity.NotebookRecord;
import training.entity.NotebookRecordImpl;
import training.proxy.ImmutableProxy;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class Lab5Task4ImmutableProxy {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        NotebookRecord rec = new NotebookRecordImpl(new Date(), "Full Name", "+38000000000");
        long almostYear = 350L * 24 * 3600 * 1000;
        rec.setDateOfBirth(new Date(new Date().getTime() - almostYear));
        NotebookRecord immutableRecord = ImmutableProxy.getImmutableProxy(rec);
        printMessage("getDate on record: " + rec.getDate());
        printMessage("getDate on immutableRecord: " + immutableRecord.getDate());
        System.out.print("Trying to setDate on record.... ");
        try {
            rec.setDate(new Date(rec.getDate().getTime() + almostYear));
            printMessage("OK");
        } catch (Exception e) {
            printMessage(e.getClass().getSimpleName());
        }
        System.out.print("Trying to setDate on immutableRecord.... ");
        try {
            immutableRecord.setDate(new Date(rec.getDate().getTime() + almostYear));
            printMessage("OK");
        } catch (Exception e) {
            printMessage(e.getClass().getSimpleName() + " : " + e.getMessage());
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
