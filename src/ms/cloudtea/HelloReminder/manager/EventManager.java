package ms.cloudtea.HelloReminder.manager;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import ms.cloudtea.HelloReminder.app.BaseActivity;
import ms.cloudtea.HelloReminder.calendar.IEventColumns;
import ms.cloudtea.HelloReminder.calendar.IReminderColumns;
import ms.cloudtea.HelloReminder.task.BaseAsyncTask;
import ms.cloudtea.HelloReminder.task.IAsyncCallback;
import ms.cloudtea.HelloReminder.util.DateUtils;
import ms.cloudtea.HelloReminder.util.Tracer;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Shahab on 1/11/14.
 */
public class EventManager {

    public void createReminder(final BaseActivity activity, final Date date, final IAsyncCallback callback) {

        new BaseAsyncTask<Void, Void, Void>(activity, true, callback) {

            @Override
            protected Void doInBackground(Void... voids) {

                long calID = PrefManager.getCalendarId(mActivity);

                long startMillis = 0;
                long endMillis = 0;
                Calendar beginTime = Calendar.getInstance();
                beginTime.setTimeInMillis(date.getTime());
                startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.setTimeInMillis(startMillis);
                endTime.add(Calendar.HOUR, 1);
                endMillis = endTime.getTimeInMillis();

                Tracer.d("start time: " + DateUtils.formatDateTime(startMillis));
                Tracer.d("end time: " + DateUtils.formatDateTime(endMillis));

                ContentResolver cr = activity.getContentResolver();
                ContentValues values = new ContentValues();


                values.put(IEventColumns.DTSTART,           startMillis);
                values.put(IEventColumns.DTEND,             endMillis);
                values.put(IEventColumns.TITLE,             "Title goes here");
                values.put(IEventColumns.DESCRIPTION,       "Desc goes here");
                values.put(IEventColumns.CALENDAR_ID,       calID);
                values.put(IEventColumns.EVENT_TIMEZONE,    TimeZone.getDefault().getID());

                Uri uri = cr.insert(Uri.parse(IEventColumns.CONTENT_URI), values);

                long eventID = Long.parseLong(uri.getLastPathSegment());

                Tracer.d("new created eventId: " + eventID);

                // now we are ready to add reminder to this event
                values.clear();

                values.put(IReminderColumns.MINUTES, 2);
                values.put(IReminderColumns.EVENT_ID, eventID);
                values.put(IReminderColumns.METHOD, IReminderColumns.METHOD_ALERT);

                cr.insert(Uri.parse(IReminderColumns.CONTENT_URI), values);

                return null;
            }

        }.execute();
    }
}
