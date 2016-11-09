package training.entity;

import training.annotation.Constructor;
import training.annotation.ReflectionInvocation;

import java.util.Date;

/**
 * This class is the implementation of interface NotebookRecord.
 * Date of birth is stored in super.date field.
 *
 * @version 1.0 08 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class NotebookRecordImpl extends Data implements NotebookRecord {
    /**
     * The value of person's full name
     */
    private String fullName;
    /**
     * The value of person's phone
     */
    private String phone;

    /**
     * Default constructor
     */
    NotebookRecordImpl() {
    }

    /**
     * Constructor for needs of laboratory work of reflection API study
     *
     * @param date
     */
    @Deprecated
    @Constructor(enabled = false)
    private NotebookRecordImpl(Date date) {
    }

    /**
     * Initializes all fields of entity
     *
     * @param dateOfBirth the value of date of birth to be set
     * @param fullName the value of person's full name to be set
     * @param phone The value of person's phone to be set
     */
    @Constructor(enabled = true)
    public NotebookRecordImpl(Date dateOfBirth, String fullName, String phone) {
        super(dateOfBirth);
        this.fullName = fullName;
        this.phone = phone;
    }

    /**
     * Implementation of interface method {@link NotebookRecord#getDayCountTillNextBirthday()}
     *
     * @return Number of days for next Birthday
     */
    @Override
    @ReflectionInvocation(enabled = true)
    public int getDayCountTillNextBirthday() {

        Date now = new Date();
        Date today = new Date(now.getTime() - (now.getTime() % MILLIS_IN_DAY));
        today.setHours(0);
        Date nextBirthday = new Date(super.getDate().getTime());

        nextBirthday.setYear(today.getYear());

        if (nextBirthday.compareTo(today) < 0) {
            nextBirthday.setYear(nextBirthday.getYear() + 1);
        }
        return (int) ((nextBirthday.getTime() - today.getTime()) / MILLIS_IN_DAY);
    }


    @Override
    public String getFullName() {
        return fullName;
    }


    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    @Override
    public String getPhone() {
        return phone;
    }


    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets a date from super class and returns it
     *
     * @return The value of date of birth
     */
    @ReflectionInvocation(enabled = true)
    private Date getDateOfBirth() {
        return super.getDate();
    }


    /**
     * Stores a date to super class
     */
    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        super.setDate(dateOfBirth);
    }

    @Override
    public String toString() {
        return "NotebookRecordImpl{" +
                "dateOfBirth='" + super.getDate() + '\'' +
                "fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NotebookRecordImpl that = (NotebookRecordImpl) o;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
