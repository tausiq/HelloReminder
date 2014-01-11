package ms.cloudtea.HelloReminder.calendar;

/**
 * Created by Shahab on 1/11/14.
 */
public interface IEventColumns {

    String CONTENT_URI = "content://com.android.calendar/events/";
    String CALENDAR_ID = "calendar_id";
    String TITLE = "title";
    String DESCRIPTION = "description";
    String EVENT_LOCATION = "eventLocation";
    String EVENT_COLOR = "eventColor";
    String EVENT_COLOR_KEY = "eventColor_index";
    String DISPLAY_COLOR = "displayColor";
    String STATUS = "eventStatus";
    String SELF_ATTENDEE_STATUS = "selfAttendeeStatus";
    String DTSTART = "dtstart";
    String DTEND = "dtend";
    String DURATION = "duration";
    String EVENT_TIMEZONE = "eventTimezone";
    String ALL_DAY = "allDay";

    String BEGIN_TIME = "beginTime";
    String END_TIME = "endTime";
}
