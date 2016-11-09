package training.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class NotebookRecordTest {
    @Test
    public void testGetDayCountTillNextBirthday() throws Exception {
        NotebookRecord rec = new NotebookRecordImpl();
        Date now = new Date();
        Date tmp = new Date(now.getTime());
        tmp.setYear(tmp.getYear() - 10);
        Date tenYearsAgo = new Date(tmp.getTime());
        Date tenYearsAnd30DaysAgo = new Date(tmp.getTime() - NotebookRecordImpl.MILLIS_IN_DAY * 30);
        Date nineYearsAnd335DaysAgo = new Date(tmp.getTime() + NotebookRecordImpl.MILLIS_IN_DAY * 30);
        rec.setDateOfBirth(tenYearsAgo);
        Assert.assertEquals(0, rec.getDayCountTillNextBirthday());
        rec.setDateOfBirth(tenYearsAnd30DaysAgo);
        Assert.assertEquals(334, rec.getDayCountTillNextBirthday());
        rec.setDateOfBirth(nineYearsAnd335DaysAgo);
        Assert.assertEquals(30, rec.getDayCountTillNextBirthday());
    }

}