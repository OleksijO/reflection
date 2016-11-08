package training;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oleksij.onysymchuk@gmail on 08.11.2016.
 */
public class NotebookRecordTest {
    @Test
    public void testGetDayCountTillNextBirthday() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NotebookRecord rec = new NotebookRecord();
        Date now = new Date();
        Date today = sdf.parse(sdf.format(now));
        today.setHours(0);
        Date tmp = new Date(today.getTime());
        tmp.setYear(tmp.getYear() - 10);
        Date tenYearsAgo = new Date(tmp.getTime());
        tenYearsAgo.setHours(0);
        Date tenYearsAnd30DaysAgo = new Date(tmp.getTime() - NotebookRecord.MILLIS_IN_DAY * 30);
        tenYearsAnd30DaysAgo.setHours(0);
        Date nineYearsAnd335DaysAgo = new Date(tmp.getTime() + NotebookRecord.MILLIS_IN_DAY * 30);
        nineYearsAnd335DaysAgo.setHours(0);

        rec.setDateOfBirth(tenYearsAgo);
        Assert.assertEquals(0, rec.getDayCountTillNextBirthday());
        rec.setDateOfBirth(tenYearsAnd30DaysAgo);
        Assert.assertEquals(334, rec.getDayCountTillNextBirthday());
        rec.setDateOfBirth(nineYearsAnd335DaysAgo);
        Assert.assertEquals(30, rec.getDayCountTillNextBirthday());
    }

}