package training;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class Data {
    protected static final long MILLIS_IN_DAY = 24 * 3600 * 1000;
    private Date date;

    public Data() {
    }

    protected Data(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data date1 = (Data) o;

        return date != null ? date.equals(date1.date) : date1.date == null;

    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                '}';
    }


}