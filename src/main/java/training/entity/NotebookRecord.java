package training.entity;

import training.annotation.ReflectionInvocation;

import java.util.Date;

/**
 * This class is interface for entity, which is necessary for creating proxies.
 *
 * @version 1.0 08 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public interface NotebookRecord {

    /**
     * Calculates number of day before next birthday date.
     *
     * @return Number of days for next Birthday
     */
    @ReflectionInvocation(enabled = true)
    int getDayCountTillNextBirthday();

    String getFullName();

    void setFullName(String fullName);

    String getPhone();

    void setPhone(String phone);

    void setDateOfBirth(Date dateOfBirth);

    Date getDate();

    void setDate(Date date);
}
