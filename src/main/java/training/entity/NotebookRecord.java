package training.entity;

import training.annotation.ReflectionInvocation;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public interface NotebookRecord {
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
