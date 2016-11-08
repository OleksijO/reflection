package training;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class NotebookRecord extends Data {
    private String fullName;
    private String phone;

    NotebookRecord() {
    }

    public NotebookRecord(Date date, String fullName, String phone) {
        super(date);
        this.fullName = fullName;
        this.phone = phone;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return super.getDate();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        super.setDate(dateOfBirth);
    }

    @Override
    public String toString() {
        return "NotebookRecord{" +
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

        NotebookRecord that = (NotebookRecord) o;
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
