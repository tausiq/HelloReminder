package ms.cloudtea.HelloReminder.calendar;

import android.net.Uri;

/**
 * Created by Shahab on 1/11/14.
 */
public interface IReminderColumns {

    String EVENT_ID = "event_id";
    String MINUTES = "minutes";
    int MINUTES_DEFAULT = -1;
    String METHOD = "method";

    int METHOD_DEFAULT = 0;
    int METHOD_ALERT = 1;
    int METHOD_EMAIL = 2;
    int METHOD_SMS = 3;
    int METHOD_ALARM = 4;

    String CONTENT_URI = "content://com.android.calendar/reminders";
}
