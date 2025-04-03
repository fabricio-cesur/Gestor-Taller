package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formateo {
    public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public String dateToString(LocalDate date) {
        return date.format(dtf);
    }

    public LocalDate stringToDate(String string) {
        return LocalDate.parse(string, dtf);
    }
}
