package temp;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MyDateFormat {

    public static String formatDate(long date, String format) {
        //Locale locale = new Locale("fr");
        Locale locale = new Locale("ru");
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
        String[] months = {
                "января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        String[] shortMonths = {
                "янв", "фев", "мар", "апр", "май", "июн",
                "июл", "авг", "сен", "окт", "ноя", "дек"};
        dfs.setMonths(months);
        dfs.setShortMonths(shortMonths);
        String[] weekdays = {"", "Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
        String[] shortWeekdays = {"", "вс", "пн", "вт", "ср", "чт", "пт", "сб"};
        dfs.setWeekdays(weekdays);
        dfs.setShortWeekdays(shortWeekdays);

        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setDateFormatSymbols(dfs);
        return sdf.format(date); // пт, 09 декабря 2016
    }
}
