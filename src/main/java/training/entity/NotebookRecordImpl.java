package training.entity;

import training.annotation.Constructor;
import training.annotation.ReflectionInvocation;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class NotebookRecordImpl extends Data implements NotebookRecord {
    private String fullName;
    private String phone;

    NotebookRecordImpl() {
    }
    @Deprecated
    @Constructor(enabled = false)
    private NotebookRecordImpl(Date date) {
    }

    @Constructor(enabled = true)
    public NotebookRecordImpl(Date date, String fullName, String phone) {
        super(date);
        this.fullName = fullName;
        this.phone = phone;
    }

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

    @ReflectionInvocation(enabled = true)
    private Date getDateOfBirth() {
        return super.getDate();
    }


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
