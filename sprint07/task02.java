import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class MyUtils {
     public static String getDateAfterToday(int years, int months, int days) {
        return LocalDate.now()
                .plus(Period.of(years, months, days))
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
