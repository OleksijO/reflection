package training.entity;

import training.annotation.Constructor;
import training.annotation.ReflectionInvocation;

import java.util.Date;

/**
 * This class is a container for storing only date. Time equals to 0:00:00.000
 *
 * @version 1.1 9 NOV 2016
 * @author oleksij.onysymchuk@gmail.com
 */
public class Data {
    protected static final long MILLIS_IN_DAY = 24 * 3600 * 1000;
    /**
     * Stores date without time
     */
    private Date date;


    /**
     * Default constructor
     */
    public Data() {
    }

    /**
     * Initializes field date
     * @param date
     */
    @Constructor(enabled = false)
    protected Data(Date date) {
        setDate(date);
    }

    @ReflectionInvocation(enabled = true)
    public Date getDate() {
        return date;
    }

    /**
     * Sets time to 00:00:00.000 and stores date to class field
     *
     * @param date the value to be stored as the only date witout time
     */
    public void setDate(Date date) {
        Date tmpDate = new Date(date.getTime() - (date.getTime()%MILLIS_IN_DAY) );
        tmpDate.setHours(0);
        this.date = tmpDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data date1 = (Data) o;

        return date != null ? date.equals(date1.date) : date1.date == null;
    }

    @ReflectionInvocation(enabled = true)
    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @ReflectionInvocation(enabled = false)

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                '}';
    }
}
