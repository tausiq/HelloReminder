package ms.cloudtea.HelloReminder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shahab on 1/11/14.
 */
public class DateUtils {

    public static String formatDateTime(Long millis) {

        if (millis <= 0) return "";

        Date date = new Date(millis);
        return new SimpleDateFormat("dd-MMM-yyyy  HH:mm").format(date);
    }

    public static String formatTime(long millis) {

        if (millis <= 0) return "";

        Date date = new Date(millis);
        return new SimpleDateFormat("HH:mm").format(date);
    }
}
