package ms.cloudtea.HelloReminder.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import ms.cloudtea.HelloReminder.app.Const;

/**
 * Created by Shahab on 1/11/14.
 */
public class PrefManager {

    public static boolean hasCalendar(Context ctx) {

        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(ctx);

        return mPref.getLong(Const.PREF_CALENDAR_ID, 0) >= 1;
    }

    public static void saveCalendar(final Context ctx, final long id, final String name) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(ctx);

                SharedPreferences.Editor editor = mPref.edit();

                editor.putLong(Const.PREF_CALENDAR_ID, id);
                editor.putString(Const.PREF_CALENDAR_NAME, name);
                editor.commit();
            }
        }).start();
    }

    public static long getCalendarId(Context ctx) {
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(ctx);

        return mPref.getLong(Const.PREF_CALENDAR_ID, 1);
    }
}
