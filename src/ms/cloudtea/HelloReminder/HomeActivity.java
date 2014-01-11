package ms.cloudtea.HelloReminder;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import ms.cloudtea.HelloReminder.app.BaseActivity;
import ms.cloudtea.HelloReminder.manager.EventManager;
import ms.cloudtea.HelloReminder.model.CalendarModel;
import ms.cloudtea.HelloReminder.task.IAsyncCallback;
import ms.cloudtea.HelloReminder.util.DialogBox;
import ms.cloudtea.HelloReminder.manager.PrefManager;
import ms.cloudtea.HelloReminder.util.UIUtils;

import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends BaseActivity implements UIUtils.CalendarChooserDialog.OnCalendarChooseListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new LoadCalendarTask().execute();
    }

    private class LoadCalendarTask extends AsyncTask<Void, Void, ArrayList<CalendarModel>> {

        // Projection array. Creating indices for this array instead of doing
        // dynamic lookups improves performance.
        final String[] EVENT_PROJECTION = new String[] {
                "_id",                           // Id column, index: 0
                UIUtils.hasICS() ? "calendar_displayName" : "displayName"     // name column, index: 1
        };

        // The indices for the projection array above.
        private static final int PROJECTION_ID_INDEX = 0;
        private static final int PROJECTION_DISPLAY_NAME_INDEX = 1;

        @Override
        protected ArrayList<CalendarModel> doInBackground(Void... voids) {
            // Run query
            Cursor cur = null;
            ContentResolver cr = HomeActivity.this.getContentResolver();
            Uri uri = Uri.parse("content://com.android.calendar/calendars");

            // Submit the query and get a Cursor object back.
            cur = cr.query(uri, EVENT_PROJECTION, null, null, null);

            ArrayList<CalendarModel> list = new ArrayList<CalendarModel>();

            while (cur != null && cur.moveToNext()) {
                long calID = 0;
                String displayName = null;

                // Get the field values
                calID = cur.getLong(PROJECTION_ID_INDEX);
                displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);

                list.add(new CalendarModel(calID, displayName));
            }

            if (cur != null) cur.close();

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<CalendarModel> calendarModels) {
            super.onPostExecute(calendarModels);

            if (calendarModels == null) return;

            ArrayList<CalendarModel> response = (ArrayList<CalendarModel>) calendarModels;

            switch (response.size()) {

                // no calendar
                case 0:
                    DialogBox.toast(getString(R.string.err_no_calendar_found));
                    break;

                // only one calendar found .. just save it as default calendar
                case 1:
                    onSelectCalendar(response.get(0).getCalendarId(), response.get(0).getDisplayName());
                    break;

                // lots of calendars, ask user to select the default calendar app
                default:
                    UIUtils.showCalendarChooserDialog(HomeActivity.this, response);
            }
        }
    }

    @Override
    public void onSelectCalendar(long id, String name) {
        PrefManager.saveCalendar(this, id, name);

        EventManager manager = new EventManager();
        manager.createReminder(this, new Date(), new IAsyncCallback() {
            @Override
            public void onComplete(Object data) {
                DialogBox.showAlert(HomeActivity.this, "", "Reminder set after 2 minutes from now");
            }
        });
    }
}
