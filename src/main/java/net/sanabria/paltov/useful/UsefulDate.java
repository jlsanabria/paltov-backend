package net.sanabria.paltov.useful;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsefulDate {

    public static String dateTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
