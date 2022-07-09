package temp;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StringArrayList {
    public static void main(String[] args) throws InterruptedException {
        LocalDate localDate = LocalDate.of(2022, Month.MARCH, 3);
        System.out.println(localDate.getMonthValue());
        Calendar calendar = new GregorianCalendar(2022, Calendar.MARCH, 3);
    }
    }
